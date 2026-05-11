class SLLNode {
    Package data;
    SLLNode next;
    SLLNode(Package d) { data = d; }
}

public class SLL {
    SLLNode head;

    /**
     * Master Registry: Appends a package to the end of the daily log.
     * Time Complexity: O(N)
     * Explanation: Because the SLL structure lacks a tail pointer, the list must be traversed
     * from the 'head' to the last element (where next is null) to append the new record.
     * * @param p The package to be recorded in the log.
     */
    public void addRecord(Package p) {
        SLLNode n = new SLLNode(p);
        if (head == null) { head = n; return; }
        SLLNode t = head;
        while (t.next != null) t = t.next;
        t.next = n;
    }

    /**
     * Master Registry: Traverses the SLL to print the daily records sequentially.
     * Time Complexity: O(N)
     * Explanation: Visits every node exactly once to display the data.
     */
    public void displayLog() {
        SLLNode t = head;
        while (t != null) {
            System.out.println(t.data);
            t = t.next;
        }
    }
}