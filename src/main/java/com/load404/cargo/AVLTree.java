package com.load404.cargo;
import java.util.*;

/**
 * AVLNode — A single node in the AVL Tree.
 * Each node stores a neighborhood name (key) and a list of package IDs
 * (vals) that are destined for that neighborhood. 
 */ 

class AVLNode {
    String key;
    List<String> vals; 
    int h;
    AVLNode l, r;

    AVLNode(String k, String v) {
        key = k;
        vals = new ArrayList<>();
        vals.add(v);
        h = 1;
    }
}

public class AVLTree {
    private AVLNode root;

    private int height(AVLNode n) { return n == null ? 0 : n.h; }
    private int getBalance(AVLNode n) { return n == null ? 0 : height(n.l) - height(n.r); }

    /**
     * Right Rotation around node. 
     * Used when the left subtree is too tall (balance > 1, left-left case).
     * The left child becomes the new subtree root.
     * Time Complexity: O(1) — only pointer re-assignments.
     */

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.l;
        AVLNode T2 = x.r;
        x.r = y;
        y.l = T2;
        y.h = Math.max(height(y.l), height(y.r)) + 1;
        x.h = Math.max(height(x.l), height(x.r)) + 1;
        return x;
    }

    /**
     * Left Rotation around node.
     * Used when the right subtree is too tall (balance < -1, right-right case).
     * The right child becomes the new subtree root.
     * Time Complexity: O(1) — only pointer re-assignments.
     */

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.r;
        AVLNode T2 = y.l;
        y.l = x;
        x.r = T2;
        x.h = Math.max(height(x.l), height(x.r)) + 1;
        y.h = Math.max(height(y.l), height(y.r)) + 1;
        return y;
    }

     /**
     * Recursively inserts a key-value pair into the subtree rooted at {@code node}
     * and re-balances using rotations if necessary.
     * Time Complexity: O(log N) — tree height is always O(log N) after balancing.
     */
    
    private AVLNode insert(AVLNode node, String key, String val) {
        if (node == null) return new AVLNode(key, val);
        if (key.equals(node.key)) {
            if (!node.vals.contains(val)) node.vals.add(val);
            return node;
        }
        if (key.compareTo(node.key) < 0) node.l = insert(node.l, key, val);
        else node.r = insert(node.r, key, val);

        node.h = 1 + Math.max(height(node.l), height(node.r));
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.l.key) < 0) return rightRotate(node);
        if (balance < -1 && key.compareTo(node.r.key) > 0) return leftRotate(node);
        if (balance > 1 && key.compareTo(node.l.key) > 0) { node.l = leftRotate(node.l); return rightRotate(node); }
        if (balance < -1 && key.compareTo(node.r.key) < 0) { node.r = rightRotate(node.r); return leftRotate(node); }
        return node;
    }

    /**
     * Public entry point: inserts a neighborhood-to-packageID mapping into the tree.
     * The key is normalised to lower-case for case-insensitive lookups.
     * Time Complexity: O(log N)
     */`    6    public void insert(String k, String v) {
        root = insert(root, k.toLowerCase(), v);
    }

    /**
     * Recursively searches for a node with the given key.
     * Time Complexity: O(log N)
     */
    private AVLNode search(AVLNode n, String k) {
        if (n == null || n.key.equals(k)) return n;
        return k.compareTo(n.key) < 0 ? search(n.l, k) : search(n.r, k);
    }

     /**
     * Public entry point: returns all package IDs destined for the given neighborhood.
     * Time Complexity: O(log N)
     */
    public List<String> search(String k) {
        if (k == null) return null;
        AVLNode n = search(root, k.toLowerCase());
        return n == null ? null : n.vals;
    }
}
