package com.load404.cargo;
import java.util.*;

/**
 * Edge Class: Represents a connection between two nodes (neighborhoods).
 */
class Edge {
    String v; // Destination node
    int w;    // Weight (distance)
    
    Edge(String v, int w) { 
        this.v = v; 
        this.w = w; 
    }
}

/**
 * City Routing System (Graph Database)
 */
public class Graph {
    Map<String, List<Edge>> g = new HashMap<>();

    public void addEdge(String s, String d, int w) {
        g.putIfAbsent(s, new ArrayList<>());
        g.putIfAbsent(d, new ArrayList<>());
        g.get(s).add(new Edge(d, w));
        g.get(d).add(new Edge(s, w));
    }
    
    /**
     * Dijkstra's Algorithm: Returns the shortest paths as a formatted string.
     */
    public String dijkstra(String start) {
        if (!g.containsKey(start)) return "Start point not found";
        
        Map<String, Integer> dist = new HashMap<>();
        for (String k : g.keySet()) dist.put(k, Integer.MAX_VALUE);
        dist.put(start, 0);
        
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(start);
        
        while (!pq.isEmpty()) {
            String u = pq.poll();
            for (Edge e : g.get(u)) {
                int nd = dist.get(u) + e.w;
                if (nd < dist.get(e.v)) {
                    dist.put(e.v, nd);
                    pq.add(e.v);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder("Shortest paths from " + start + ":\n");
        for (Map.Entry<String, Integer> entry : dist.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" km\n");
        }
        return sb.toString();
    }

    /**
     * Prim's Minimum Spanning Tree (MST) Algorithm.
     * Returns the MST description and total weight.
     */
    public String prim() {
        if (g.isEmpty()) return "Map is empty";
        
        Set<String> vis = new HashSet<>();
        // Edge representation for PQ: [source, destination, weight]
        PriorityQueue<Object[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (int)a[2]));
        
        String start = g.keySet().iterator().next();
        vis.add(start);
        
        for (Edge e : g.get(start)) {
            pq.add(new Object[]{start, e.v, e.w});
        }
            
        int total = 0;
        StringBuilder sb = new StringBuilder("Optimal Infrastructure Network (MST):\n");
        
        while (!pq.isEmpty()) {
            Object[] cur = pq.poll();
            String u = (String)cur[0];
            String v = (String)cur[1];
            int w = (int)cur[2];
                
            if (vis.contains(v)) continue;
            
            vis.add(v);
            total += w;
            sb.append(u).append(" <-> ").append(v).append(" (").append(w).append(" km)\n");
            
            for (Edge e : g.get(v)) {
                if (!vis.contains(e.v)) {
                    pq.add(new Object[]{v, e.v, e.w});
                }
            }
        }
        sb.append("Total network length: ").append(total).append(" km");
        return sb.toString();
    }
}
