import java.util.*;

/**
 * Dispatch Preparation: Standard Delivery (Queue)
 * Utilizes a FIFO (First-In, First-Out) structure. The first package to arrive 
 * at the sorting station is the first one moved to the loading dock.
 */
public class QueueDS {
    // Using LinkedList as the underlying structure for the Queue
    Queue<Package> q = new LinkedList<>();

    /**
     * Adds a package to the back of the delivery line.
     * Time Complexity: O(1)
     * Explanation: Enqueuing in a LinkedList-backed Queue takes constant time 
     * as it just appends the element to the tail.
     * * @param p The package to be enqueued.
     */
    public void enqueue(Package p) {
        q.add(p);
    }

    /**
     * Removes and returns the package at the front of the delivery line.
     * Time Complexity: O(1)
     * Explanation: Dequeuing from a LinkedList-backed Queue takes constant time 
     * as it just removes the element from the head.
     * * @return The package that is next in line, or null if the queue is empty.
     */
    public Package dequeue() {
        return q.poll();
    }

    /**
     * Checks if the delivery queue is empty.
     * Time Complexity: O(1)
     * * @return true if the queue has no packages, false otherwise.
     */
    public boolean isEmpty() {
        return q.isEmpty();
    }
}