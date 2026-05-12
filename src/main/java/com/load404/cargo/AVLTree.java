
package com.load404.cargo;/**
 * AVL Node Class: Represents a single node in the Address Directory.
 */
class AVLNode {
    String key, val;
    int h; // Height of the node
    AVLNode l, r; // Left and Right child pointers

    AVLNode(String k, String v) {
        key = k;
        val = v;
        h = 1;
    }
}

/**
 * Address Directory (Database)
 * Uses a Self-Balancing AVL Tree to efficiently store and manage neighborhood names 
 * (as keys) and customer IDs (as values). Guarantees O(log N) time complexity for 
 * insertions and lookups.
 */
public class AVLTree {
    AVLNode root;

    /**
     * Helper Function: Returns the height of a specific node.
     * Time Complexity: O(1)
     */
    int h(AVLNode n) {
        return n == null ? 0 : n.h;
    }

    /**
     * Helper Function: Calculates the balance factor of a node to check if the tree is unbalanced.
     * Time Complexity: O(1)
     */
    int bal(AVLNode n) {
        return n == null ? 0 : h(n.l) - h(n.r);
    }

    /**
     * Self-Balancing Logic: Right Rotation (rR)
     * Fixes Left-Left (LL) heavy imbalance.
     * Time Complexity: O(1)
     */
    AVLNode rR(AVLNode y) {
        AVLNode x = y.l, t = x.r;
        x.r = y;
        y.l = t;
        // Update heights after rotation
        y.h = Math.max(h(y.l), h(y.r)) + 1;
        x.h = Math.max(h(x.l), h(x.r)) + 1;
        return x;
    }

    /**
     * Self-Balancing Logic: Left Rotation (lR)
     * Fixes Right-Right (RR) heavy imbalance.
     * Time Complexity: O(1)
     */
    AVLNode lR(AVLNode x) {
        AVLNode y = x.r, t = y.l;
        y.l = x;
        x.r = t;
        // Update heights after rotation
        x.h = Math.max(h(x.l), h(x.r)) + 1;
        y.h = Math.max(h(y.l), h(y.r)) + 1;
        return y;
    }

    /**
     * Recursive Insertion & Self-Balancing Method.
     * Time Complexity: O(log N)
     * Explanation: Recursively finds the correct position for the new node, updates heights, 
     * and performs necessary rotations (LL, RR, LR, RL) if the balance factor is violated.
     */
    AVLNode insert(AVLNode n, String k, String v) {
        // 1. Perform standard BST insertion
        if (n == null) return new AVLNode(k, v);
        
        if (k.compareTo(n.key) < 0) 
            n.l = insert(n.l, k, v);
        else 
            n.r = insert(n.r, k, v);

        // 2. Update node height
        n.h = 1 + Math.max(h(n.l), h(n.r));

        // 3. Get balance factor and check for 4 unbalance cases
        int b = bal(n);

        // Left-Left Case
        if (b > 1 && k.compareTo(n.l.key) < 0) return rR(n);
        // Right-Right Case
        if (b < -1 && k.compareTo(n.r.key) > 0) return lR(n);
        // Left-Right Case
        if (b > 1 && k.compareTo(n.l.key) > 0) { 
            n.l = lR(n.l); 
            return rR(n); 
        }
        // Right-Left Case
        if (b < -1 && k.compareTo(n.r.key) < 0) { 
            n.r = rR(n.r); 
            return lR(n); 
        }

        return n; // Return the (unchanged) node pointer
    }

    /**
     * Public wrapper method to insert a new address record into the tree.
     */
    public void insert(String k, String v) {
        root = insert(root, k, v);
    }

    /**
     * Recursive Search Method.
     * Time Complexity: O(log N)
     * Explanation: Quickly navigates left or right based on alphabetical order of the keys.
     */
    public AVLNode search(AVLNode n, String k) {
        if (n == null || n.key.equals(k)) return n;
        return k.compareTo(n.key) < 0 ? search(n.l, k) : search(n.r, k);
    }

    /**
     * Public wrapper method to search for a specific neighborhood and return its corresponding data.
     */
    public String search(String k) {
        AVLNode n = search(root, k);
        return n == null ? null : n.val;
    }
}
