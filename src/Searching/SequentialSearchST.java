/******************************************************************************
 * Compilation:  javac SequentialSearchST.java
 * Execution:    java SequentialSearchST
 * Dependencies: -
 * Author:		 Parth Vikani
 *
 * ========
 * API:
 * ========
 * public int size()
 * public boolean isEmpty()
 * public boolean contains(Key key)
 * public void put(Key key, Value value)
 * public Value get(Key key)
 * public void delete(Key key)
 ******************************************************************************/
public class SequentialSearchST<Key extends Comparable<Key>, Value> {

    private int n;      // number of elements in symbol table
    private Node first; // point to first element

    // Helper linked list class
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("Null argument for contains() function");
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if(key == null) throw new NullPointerException("Null argument for add() function");
        if(value == null){
            delete(key);
            return;
        }
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        n++;
    }

    public Value get(Key key) {
        if(key == null) throw  new NullPointerException("Null argument for get() function");
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        if(key == null) throw  new NullPointerException("Null argument for delete() function");
        first = delete(first, key);
    }

    private Node delete(Node x, Key key){
        if(x == null) {
            return null;
        }
        if(key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public static void main(String[] args) {
        // Create symbol table
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();

        // Insert key-value pair
        st.put("z", 23);
        st.put("b", 3);
        st.put("a", 22);
        st.put("x", 2);
        st.put("r", 1);

        System.out.println(st.get("a"));
        System.out.println(st.get("b"));
        System.out.println(st.get("x"));
        System.out.println(st.get("r"));
        System.out.println(st.get("z"));

        // Key which are not present in symbol table will return null
        System.out.println(st.get("A"));

    }
}
