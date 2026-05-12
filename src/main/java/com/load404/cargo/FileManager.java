package com.load404.cargo;
import java.io.*;
import java.util.*;

public class FileManager {

    public static void ensureFileExists(String f) {
        File file = new File(f);
        if (!file.exists()) {
            System.out.println("File " + f + " missing. Extracting from resources...");
            try (InputStream is = FileManager.class.getClassLoader().getResourceAsStream(f);
                 OutputStream os = new FileOutputStream(file)) {
                if (is != null) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Extracted " + f + " to " + file.getAbsolutePath());
                } else {
                    file.createNewFile();
                    System.out.println("Created empty " + f);
                }
            } catch (IOException e) {
                System.err.println("Failed to ensure file " + f + ": " + e.getMessage());
            }
        }
    }

    private static BufferedReader getReader(String f) throws Exception {
        ensureFileExists(f);
        return new BufferedReader(new FileReader(f));
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
