package com.load404.cargo;

/**
 * Dispatch Preparation: Truck Loading (Stack)
 * Utilizes a LIFO (Last-In, First-Out) structure.
 */
public class StackDS {
    private class Node {
        Package data;
        Node next;
        Node(Package data) { this.data = data; }
    }

    private Node top;

    /**
     * Loads a package into the delivery van.
     * Time Complexity: O(1)
     */
    public void push(Package p) {
        Node newNode = new Node(p);
        newNode.next = top;
        top = newNode;
    }

    /**
     * Unloads the most recently loaded package from the delivery van.
     * Time Complexity: O(1)
     */
    public Package pop() {
        if (top == null) return null;
        Package p = top.data;
        top = top.next;
        return p;
    }

    /**
     * Checks if the delivery stack is empty.
     */
    public boolean isEmpty() {
        return top == null;
    }
}
