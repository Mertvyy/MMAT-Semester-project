import java.util.*;

/**
 * Project: Urban Logistics & Distribution System
 * Company: LOAD404 CARGO
 *
 * Main Execution Class
 * Orchestrates the entire logistics pipeline: reading files, processing packages 
 * through the warehouse (Buffer -> Queue -> Stack -> Log), and calculating routes.
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        // 1. Project Identity (Branding) - Required for Rubric
        System.out.println("==========================================");
        System.out.println("      LOAD404 CARGO LOGISTICS SYSTEM      ");
        System.out.println("==========================================");
        System.out.println();

        // 2. Data Initialization
        // Read incoming packages and city map from text files
        List<Package> pkgs = FileManager.readPackages("packageData.txt");
        Graph g = FileManager.readMap("mapData.txt");

        // Initialize Data Structures
        DLL buffer = new DLL();      // Intake Buffer (O(1) insertion)
        QueueDS q = new QueueDS();   // Standard Delivery Queue (FIFO)
        StackDS st = new StackDS();  // Truck Loading Stack (LIFO)
        SLL log = new SLL();         // Master Registry (Immutable daily log)
        AVLTree avl = new AVLTree(); // Address Directory (Self-balancing)

        // 3. The Warehouse Pipeline

        // Step A: Packages arrive and are added to the Intake Buffer and Address Directory
        for (Package p : pkgs) {
            buffer.insertAtTail(p);
            avl.insert(p.destination, p.id);
        }

        // Step B: Packages move from the Intake Buffer to the Sorting Station (Queue)
        Package currentPackage;
        while ((currentPackage = buffer.removeFromHead()) != null) {
            q.enqueue(currentPackage);
        }

        // Step C: Packages are loaded from the Sorting Station into the Delivery Van (Stack)
        while (!q.isEmpty()) {
            st.push(q.dequeue());
        }

        // Step D: Packages are dispatched, and records are appended to the Master Registry (SLL)
        System.out.println("--- Daily Dispatch Log ---");
        while ((currentPackage = st.pop()) != null) {
            log.addRecord(currentPackage);
        }
        
        // Print the immutable daily log for auditing
        log.displayLog();
        System.out.println();

        // 4. City Routing Optimizations

        // Calculate the fastest individual delivery routes
        System.out.println("--- Dijkstra: Shortest Paths from Meydan ---");
        g.dijkstra("Meydan");
        System.out.println();

        // Calculate the most efficient overall infrastructure network
        System.out.println("--- Prim: Minimum Spanning Tree (MST) ---");
        g.prim();
    }
}
