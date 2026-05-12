package com.load404.cargo;

/**
 * Cargo Package Model
 * Represents a single delivery item in the logistics system.
 * Acts as a simple data model (POJO) to hold the package ID and its destination.
 */
public class Package {

    public String id;
    public String destination;

    public Package() {}

    public Package(String id, String destination) {
        this.id = id;
        this.destination = destination;
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
