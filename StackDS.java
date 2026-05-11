import java.util.*;

/**
 * Dispatch Preparation: Truck Loading (Stack)
 * Utilizes a LIFO (Last-In, First-Out) structure. Simulates a narrow delivery van 
 * where the last package loaded into the back of the van is the first one 
 * the driver unloads at the next stop.
 */
public class StackDS {
    // Using Java's built-in Stack structure
    Stack<Package> s = new Stack<>();

    /**
     * Loads a package into the delivery van.
     * Time Complexity: O(1)
     * Explanation: Pushing an element onto the top of a stack takes constant time.
     * * @param p The package to be loaded into the truck.
     */
    public void push(Package p) {
        s.push(p);
    }

    /**
     * Unloads the most recently loaded package from the delivery van.
     * Time Complexity: O(1)
     * Explanation: Popping an element from the top of a stack takes constant time.
     * * @return The unloaded package, or null if the truck is empty.
     */
    public Package pop() {
        return s.isEmpty() ? null : s.pop();
    }
}