import java.util.*;

/**
 * Edge Class: Represents a directed connection between two nodes (neighborhoods).
 */
class Edge {
    String v; // Destination node (vertex)
    int w;    // Weight (distance in KM)
    
    Edge(String v, int w) { 
        this.v = v; 
        this.w = w; 
    }
}

/**
 * City Routing System (Graph Database)
 * Represents the logistics network for the city using an Adjacency List.
 */
public class Graph {
    // Adjacency list representation of the city map
    Map<String, List<Edge>> g = new HashMap<>();

    /**
     * Adds a bidirectional (undirected) road between two neighborhoods.
     * Time Complexity: O(1)
     * Explanation: HashMap insertion and ArrayList appending operate in constant time.
     * * @param s Source neighborhood
     * @param d Destination neighborhood
     * @param w Distance (weight) in kilometers
     */
    public void addEdge(String s, String d, int w) {
        g.putIfAbsent(s, new ArrayList<>());
        g.putIfAbsent(d, new ArrayList<>());
        g.get(s).add(new Edge(d, w));
        g.get(d).add(new Edge(s, w));
    }

    /**
     * Dijkstra's Algorithm: Calculates the shortest paths from the starting point 
     * (Warehouse/Meydan) to all other neighborhoods.
     * Time Complexity: O((V + E) log V) where V is vertices and E is edges.
     * Explanation: Uses a PriorityQueue to efficiently fetch the next closest neighborhood.
     * * @param start The starting hub (e.g., "Meydan")
     */
    public void dijkstra(String start) {
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
        
        for (String k : dist.keySet()) {
            System.out.println(k + " " + dist.get(k));
        }
    }

    /**
     * Prim's Minimum Spanning Tree (MST) Algorithm.
     * Calculates the most efficient overall infrastructure network for the city.
     * Time Complexity: O(E log V)
     * Explanation: Greedily selects the minimum weight edge that connects a visited 
     * node to an unvisited one. Uses hashcodes to identify strings in the priority queue.
     */
    public void prim() {
        Set<String> vis = new HashSet<>();
        // Priority Queue stores arrays as: [source_hash, destination_hash, weight]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        
        String start = g.keySet().iterator().next();
        vis.add(start);
        
        for (Edge e : g.get(start)) {
            pq.add(new int[]{start.hashCode(), e.v.hashCode(), e.w});
        }
            
        int total = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            String v = null;
            
            // Reverse lookup: Find the destination string using its hashcode
            for (String k : g.keySet()) {
                if (k.hashCode() == cur[1]) v = k;
            }
                
            if (vis.contains(v)) continue;
            
            vis.add(v);
            total += cur[2];
            
            for (Edge e : g.get(v)) {
                if (!vis.contains(e.v)) {
                    pq.add(new int[]{v.hashCode(), e.v.hashCode(), e.w});
                }
            }
        }
        System.out.println("MST weight " + total);
    }
}