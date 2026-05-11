class DLLNode {
    Package data;
    DLLNode prev, next;
    DLLNode(Package d) { data = d; }
}

public class DLL {
    DLLNode head, tail;

    /**
     * Intake Buffer: It appends the newly arrived packet to the end of the buffer.
     * Time Complexity : O(1) Because the ‘tail’ pointer is used, 
     * the element is appended directly to the end of the list without having to traverse it.
     */
     
    public void insertAtTail(Package p) {
        DLLNode n = new DLLNode(p);
        if (head == null) { head = tail = n; return; }
        tail.next = n;
        n.prev = tail;
        tail = n;
    }

    /**
     * Intake Buffer: It removes the package to be processed from the front of the conveyor belt.
     * Time Complexity (Zaman Karmaşıklığı): O(1),Only the ‘head’ pointer is moved to the next node.
     * 
     */
    public Package removeFromHead() {
        if (head == null) return null;
        Package d = head.data;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
        return d;
    }
}