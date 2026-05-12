package com.load404.cargo;
import java.io.*;
import java.util.*;

/**
 * File Management System
 * Handles the reading and parsing of system configuration files.
 */
public class FileManager {

    private static BufferedReader getReader(String f) throws Exception {
        File file = new File(f);
        if (file.exists()) {
            return new BufferedReader(new FileReader(file));
        } else {
            InputStream is = FileManager.class.getClassLoader().getResourceAsStream(f);
            if (is == null) throw new FileNotFoundException("File not found: " + f);
            return new BufferedReader(new InputStreamReader(is));
        }
    }

    public static List<Package> readPackages(String f) throws Exception {
        List<Package> l = new ArrayList<>();
        BufferedReader br = getReader(f);
        String s;
        
        while ((s = br.readLine()) != null) {
            if (s.startsWith("#") || s.trim().isEmpty()) continue;
            String[] p = s.trim().split("\\s+"); 
            if (p.length >= 2) {
                l.add(new Package(p[0], p[1])); 
            }
        }
        br.close();
        return l;
    }

    public static Graph readMap(String f) throws Exception {
        Graph g = new Graph();
        BufferedReader br = getReader(f);
        String s;
        
        while ((s = br.readLine()) != null) {
            if (s.startsWith("#") || s.trim().isEmpty()) continue;
            String[] p = s.trim().split("\\s+");
            if (p.length >= 3) {
                g.addEdge(p[0], p[1], Integer.parseInt(p[2]));
            }
        }
        br.close();
        return g;
    }
}
