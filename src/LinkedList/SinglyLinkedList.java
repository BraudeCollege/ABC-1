/******************************************************************************
 * Compilation:  javac SinglyLinkedList.java
 * Execution:    java SinglyLinkedList
 * Dependencies: -
 * Author:		 Parth Vikani
 ******************************************************************************/

import java.util.Iterator;

public class SinglyLinkedList<Item> implements Iterable<Item> {

    private int n;         // number of elements on linked list
    private Node first;    // beginning of linked list
    private Node last;     // end of linked list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public void addFirst(Item item) {
        first = new Node(item, first);
        if (last == null)
            last = first;
        n++;
    }

    public void addLast(Item item) {
        Node newNode = new Node(item, null);
        if (last != null)
            last.next = newNode;
        last = newNode;
        if (first == null)
            first = last;
        n++;
    }

    public void addBefore(int index, Item element) {
        checkPositionIndex(index);
        if (index == 1)
            addFirst(element);
        else
            linkBefore(element, node(index));
    }

    private void linkBefore(Item item, Node prev) {
        final Node nextNode = prev.next;
        final Node newNode = new Node(item, nextNode);
        prev.next = newNode;
        n++;
    }

    private Node node(int index) {
        Node x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isPositionIndex(int index) {
        return index >= 1 && index <= n;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + n;
    }

    public void print() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    public Iterator iterator() {
        return new Iterator() {
            Node temp = first;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public Item next() {
                Item item = temp.item;
                temp = temp.next;
                return item;
            }
        };
    }
}
