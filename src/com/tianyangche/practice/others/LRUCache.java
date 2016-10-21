package com.tianyangche.practice.others;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianyangche on 4/20/16.
 */
class CacheNode {
    public int key;
    public int value;
    public CacheNode prev;
    public CacheNode next;
    public CacheNode(int k, int v) {
        this.key = k;
        this.value = v;
    }
}
public class LRUCache {

    CacheNode head;
    CacheNode tail;
    Map<Integer, CacheNode> nodes;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.head = null;
        this.tail = null;
        this.nodes = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        if (!nodes.containsKey(key)) {
            return -1;
        }
        CacheNode node = nodes.get(key);
        removeFromList(node);
        insertIntoFront(node);
        return node.value;
    }


    public void set(int key, int value) {
        CacheNode node = null;
        if (nodes.containsKey(key)) {
            node = nodes.get(key);
            node.value = value;
            removeFromList(node);
            insertIntoFront(node);
        } else {
            node = new CacheNode(key, value);
            if (size == capacity) {
                removeFromList(tail);
            }
            insertIntoFront(node);
        }
    }


    private void removeFromList(CacheNode node) {
        if (size == 1) {
            head = null;
            tail = null;
            nodes.remove(node.key);
            size--;
            return;
        }
        if (head == node) {
            head = node.next;
            node.prev = null;
            head.next = null;
        } else if (tail == node) {
            tail = node.prev;
            node.next = null;
            tail.prev = null;
        } else {
            CacheNode prev = node.prev;
            CacheNode next = node.next;
            prev.next = node.next;
            next.prev = node.prev;
        }
        nodes.remove(node.key);
        size--;
    }

    private void insertIntoFront(CacheNode node) {
        if (size == 0) {
            head = tail = node;
            nodes.put(node.key, node);
            size++;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
        nodes.put(node.key, node);
        size++;
    }

    public void printLRUCache() {
        CacheNode curr = head;
        while (curr != null) {
            System.out.print(curr.key + "\t");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.set(3, 3);
        cache.printLRUCache();
        cache.set(4, 4);
        cache.get(4);

        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.set(5, 5);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }
 }
