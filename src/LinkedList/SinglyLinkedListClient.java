/******************************************************************************
 * Compilation:  javac SinglyLinkedList.java
 * Execution:    java SinglyLinkedList
 * Dependencies: -
 * Author:		 Parth Vikani
 ******************************************************************************/

import java.util.Iterator;

public class SinglyLinkedListClient {
    public static void main(String arg[]) {

        // Create singly linked list
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();

        // Add items in linked list
        sl.addLast(30);
        sl.addFirst(1);

        // Traverse linked list
        Iterator<Integer> itr = sl.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}