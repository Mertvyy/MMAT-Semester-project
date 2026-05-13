package com.load404.cargo;
import java.util.*;

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

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.l;
        AVLNode T2 = x.r;
        x.r = y;
        y.l = T2;
        y.h = Math.max(height(y.l), height(y.r)) + 1;
        x.h = Math.max(height(x.l), height(x.r)) + 1;
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.r;
        AVLNode T2 = y.l;
        y.l = x;
        x.r = T2;
        x.h = Math.max(height(x.l), height(x.r)) + 1;
        y.h = Math.max(height(y.l), height(y.r)) + 1;
        return y;
    }

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

    public void insert(String k, String v) {
        root = insert(root, k.toLowerCase(), v);
    }

    private AVLNode search(AVLNode n, String k) {
        if (n == null || n.key.equals(k)) return n;
        return k.compareTo(n.key) < 0 ? search(n.l, k) : search(n.r, k);
    }

    public List<String> search(String k) {
        if (k == null) return null;
        AVLNode n = search(root, k.toLowerCase());
        return n == null ? null : n.vals;
    }
}
