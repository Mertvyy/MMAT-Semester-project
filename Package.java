/**
 * Cargo Package Model
 * Represents a single delivery item in the logistics system.
 * Acts as a simple data model (POJO) to hold the package ID and its destination.
 */
public class Package {
    
    String id;          // Unique identifier (e.g., PKG_KYS_001)
    String destination; // Target neighborhood (e.g., Talas, Belsin)

    /**
     * Constructor to initialize a new package.
     * * @param id The unique package ID.
     * @param dest The destination neighborhood.
     */
    public Package(String id, String dest) {
        this.id = id;
        this.destination = dest;
    }

    /**
     * Returns a string representation of the package for easy console printing.
     * Time Complexity: O(1)
     */
    @Override
    public String toString() {
        return id + " -> " + destination;
    }
}