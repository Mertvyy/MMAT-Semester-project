package com.load404.cargo;

/**
 * Dispatch Preparation: Standard Delivery (Queue)
 * Utilizes a FIFO (First-In, First-Out) structure.
 */
public class QueueDS {
    private class Node {
        Package data;
        Node next;
        Node(Package data) { this.data = data; }
    }

    private Node head, tail;

    /**
     * Adds a package to the back of the delivery line.
     * Time Complexity: O(1)
     */
    public void enqueue(Package p) {
        Node newNode = new Node(p);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Removes and returns the package at the front of the delivery line.
     * Time Complexity: O(1)
     */
    public Package dequeue() {
        if (head == null) return null;
        Package p = head.data;
        head = head.next;
        if (head == null) tail = null;
        return p;
    }

    /**
     * Checks if the delivery queue is empty.
     */
    public boolean isEmpty() {
        return head == null;
    }
}
