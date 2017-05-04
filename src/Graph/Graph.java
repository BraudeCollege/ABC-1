/******************************************************************************
 * Compilation:  javac Graph.java
 * Execution:    java Graph
 * Dependencies: -
 * Author:		 Parth Vikani
 *
 * ========
 * API:
 * ========
 * public Graph(int V)
 * public int V()
 * public int E()
 * public void addEdge(int v, int w)
 * public Iterable<Integer> adj(int v)
 * public String toString()
 *
 * Unit Test Output:
 * 5 vertices, 9 edges 
 * 0: 4 3 2 1 
 * 1: 2 2 4 0 
 * 2: 3 1 1 0 
 * 3: 2 4 0 
 * 4: 3 1 0 
 ******************************************************************************/
 
import java.util.Iterator;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("Vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // Unit Test
    public static void main(String[] args) {

        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 3);
        G.addEdge(0, 4);
        G.addEdge(1, 4);
        G.addEdge(1, 2);
        G.addEdge(2, 1);
        G.addEdge(3, 4);
        G.addEdge(3, 2);
        System.out.println(G);
    }
}

/***************************************************************************
* Utility Bag class - for adjacency-lists representation, 
* which is a vertex indexed array of Bag objects.
***************************************************************************/
class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty() { return first == null;    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        first = new Node(item, first);
        N++;
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