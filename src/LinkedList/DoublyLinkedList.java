/******************************************************************************
 * Compilation:  javac DoublyLinkedList.java
 * Execution:    java DoublyLinkedList
 * Dependencies: -
 * Author:		 Parth Vikani
 *
 * ========
 * API:
 * ========
 * public boolean isEmpty()
 * public int size()
 * public void add(Item item)
 * public ListIterator<Item> iterator()
 * public String toString()
 *
 * Unit Test Output:
 *
 * 0 1 2 3 4
 * Add 1 to each element via next() and set()
 * 1 2 3 4 5
 * Multiply each element by 5 via previous() and set()
 * 5 10 15 20 25
 * Remove elements that are a multiple of 4 via next() and remove()
 * 5 10 15 25
 * Remove elements that are even via previous() and remove()
 *  5 15 25
 * Add elements via next() and add()
 * 5 6 15 16 25 26
 * Add elements via previous() and add()
 * 100 5 120 6 300 15 320 16 500 25 520 26
 ******************************************************************************/

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {

    private int n;
    private Node pre;     // before first item
    private Node post;    // after last item

    public DoublyLinkedList() {
        pre = new Node(null, null, post);
        post = new Node(null, pre, null);
    }

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // add the item to the list
    public void add(Item item) {
        Node last = post.prev;
        Node x = new Node(item, last, post);
        last.next = x;
        post.prev = x;
        n++;
    }

    public ListIterator<Item> iterator() {
        return new ListIterator<Item>() {

            private Node current = pre.next;       // the node that is returned by next()
            private Node lastAccessed = null;      // the last node to be returned by prev() or next()
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < n;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                lastAccessed = current;
                Item item = current.item;
                current = current.next;
                index++;
                return item;
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public Item previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                current = current.prev;
                index--;
                lastAccessed = current;
                return current.item;
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            // remove the element that was last accessed by next() or previous()
            // condition: no calls to remove() or add() after last call to next() or previous()
            public void remove() {
                if (lastAccessed == null) throw new IllegalStateException();
                Node x = lastAccessed.prev;
                Node y = lastAccessed.next;
                x.next = y;
                y.prev = x;
                n--;
                if (current == lastAccessed)
                    current = y;
                else
                    index--;
                lastAccessed = null;
            }

            @Override
            // replace the item of the element that was last accessed by next() or previous()
            // condition: no calls to remove() or add() after last call to next() or previous()
            public void set(Item item) {
                if (lastAccessed == null) throw new IllegalStateException();
                lastAccessed.item = item;
            }

            @Override
            public void add(Item item) {
                Node x = current.prev;
                Node z = current;
                Node y = new Node(item, x, z);
                x.next = y;
                z.prev = y;
                n++;
                index++;
                lastAccessed = null;
            }
        };
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    // a test client
    public static void main(String[] args) {

        // add elements 0, 1, ..., n
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 5; i++)
            list.add(i);
        System.out.println(list);

        // get iterator for our linked list
        ListIterator<Integer> iterator = list.iterator();

        System.out.println("Add 1 to each element via next() and set()");
        while (iterator.hasNext()) {
            int x = iterator.next();
            iterator.set(x + 1);
        }
        System.out.println(list);

        System.out.println("Multiply each element by 5 via previous() and set()");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            iterator.set(x * 5);
        }
        System.out.println(list);

        System.out.println("Remove elements that are a multiple of 4 via next() and remove()");
        while (iterator.hasNext()) {
            int x = iterator.next();
            if (x % 4 == 0) iterator.remove();
        }
        System.out.println(list);

        System.out.println("Remove elements that are even via previous() and remove()");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            if (x % 2 == 0) iterator.remove();
        }
        System.out.println(list);

        System.out.println("Add elements via next() and add()");
        while (iterator.hasNext()) {
            int x = iterator.next();
            iterator.add(x + 1);
        }
        System.out.println(list);

        System.out.println("Add elements via previous() and add()");
        while (iterator.hasPrevious()) {
            int x = iterator.previous();
            iterator.add(x * 20);
            iterator.previous();
        }
        System.out.println(list);

    }
}