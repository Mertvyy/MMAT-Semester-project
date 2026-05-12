package com.load404.cargo;
import java.io.*;
import java.util.*;

/**
 * File Management System
 * Handles the reading and parsing of system configuration files (packages and maps).
 */
public class FileManager {

    /**
     * Reads the daily incoming packages from a text file and generates Package objects.
     * Time Complexity: O(N) where N is the number of lines in the file.
     * * @param f The name/path of the text file (e.g., "packageData.txt")
     * @return A list of initialized Package objects ready for the Intake Buffer
     * @throws Exception if file reading fails
     */
    public static List<Package> readPackages(String f) throws Exception {
        List<Package> l = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String s;
        
        while ((s = br.readLine()) != null) {
            // Skip comment lines and empty lines
            if (s.startsWith("#") || s.trim().isEmpty()) continue;
            
            // Using \\s+ to safely split by one OR MORE spaces/tabs
            String[] p = s.trim().split("\\s+"); 
            
            // p[0] is Package ID, p[1] is Destination
            l.add(new Package(p[0], p[1])); 
        }
        
        br.close(); // Crucial: Close the file reader to prevent memory leaks
        return l;
    }

    /**
     * Reads the city network map from a text file and constructs the Graph database.
     * Time Complexity: O(N) where N is the number of lines in the file.
     * * @param f The name/path of the text file (e.g., "mapData.txt")
     * @return The populated Graph object representing Kayseri's delivery network
     * @throws Exception if file reading fails or number parsing fails
     */
    public static Graph readMap(String f) throws Exception {
        Graph g = new Graph();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String s;
        
        while ((s = br.readLine()) != null) {
            // Skip comment lines and empty lines
            if (s.startsWith("#") || s.trim().isEmpty()) continue;
            
            // Using \\s+ to safely split by one OR MORE spaces/tabs
            String[] p = s.trim().split("\\s+");
            
            // p[0] is Source, p[1] is Destination, p[2] is Distance Weight
            g.addEdge(p[0], p[1], Integer.parseInt(p[2]));
        }
        
        br.close(); // Crucial: Close the file reader to prevent memory leaks
        return g;
    }
}
