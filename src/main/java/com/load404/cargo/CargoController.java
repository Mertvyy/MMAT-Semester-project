package com.load404.cargo;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.io.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CargoController {

    private DLL buffer = new DLL();
    private SLL log = new SLL();
    private AVLTree avl = new AVLTree();
    private Graph graph = new Graph();
    private QueueDS sortingQueue = new QueueDS();
    private StackDS truckStack = new StackDS();

    public CargoController() throws Exception {
        loadData();
    }

    private void loadData() {
        try {
            List<Package> pkgs = FileManager.readPackages("packageData.txt");
            for (Package p : pkgs) {
                buffer.insertAtTail(p);
                avl.insert(p.destination, p.id);
            }
            graph = FileManager.readMap("mapData.txt");
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.err.println("Critical Error loading initial data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/warehouse/buffer")
    public List<Package> getBuffer() {
        List<Package> out = new ArrayList<>();
        DLLNode t = buffer.head;
        while (t != null) {
            out.add(t.data);
            t = t.next;
        }
        return out;
    }

    @PostMapping("/warehouse/step1-to-queue")
    public String moveToQueue() {
        Package p = buffer.removeFromHead();
        if (p == null) return "Buffer is empty";
        sortingQueue.enqueue(p);
        return "Moved " + p.id + " to Sorting Queue (FIFO)";
    }

    @PostMapping("/warehouse/step2-to-stack")
    public String moveToStack() {
        Package p = sortingQueue.dequeue();
        if (p == null) return "Sorting Queue is empty";
        truckStack.push(p);
        return "Loaded " + p.id + " into Truck Stack (LIFO)";
    }

    @PostMapping("/warehouse/step3-to-log")
    public String dispatch() {
        Package p = truckStack.pop();
        if (p == null) return "Truck is empty";
        log.addRecord(p);
        return "Dispatched " + p.id + ". Record added to Master Registry (SLL)";
    }

    @GetMapping("/warehouse/log")
    public List<Package> getLog() {
        List<Package> out = new ArrayList<>();
        SLLNode t = log.head;
        while (t != null) {
            out.add(t.data);
            t = t.next;
        }
        return out;
    }

    @PostMapping("/shipment/add")
    public String add(@RequestBody Package p) {
        if (p.id == null || p.destination == null) return "Invalid data";
        
        buffer.insertAtTail(p);
        avl.insert(p.destination, p.id);
        
        // Persist to file
        try (FileWriter fw = new FileWriter("packageData.txt", true)) {
            fw.write("\n" + p.id + " " + p.destination);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
            return "Added to memory, but file write failed";
        }
        
        return "Package " + p.id + " registered in intake buffer and saved to file";
    }

    @GetMapping("/address/search")
    public String search(@RequestParam String key) {
        String res = avl.search(key);
        return (res == null) ? "No shipment found for " + key : "Last Shipment to " + key + ": " + res;
    }

    @GetMapping("/route/shortest")
    public String shortestPath(@RequestParam String start) {
        return graph.dijkstra(start);
    }

    @GetMapping("/route/mst")
    public String getMST() {
        return graph.prim();
    }
}
