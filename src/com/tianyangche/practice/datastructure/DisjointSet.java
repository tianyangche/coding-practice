package com.tianyangche.practice.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianyangche on 3/5/16.
 *
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 *
 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 *
 */

public class DisjointSet {
    class Node {
        int data;
        int rank;
        Node parent;
    }

    private Map<Integer, Node> map = new HashMap<>();

    public void makeSet(int data) {
        Node node = new Node();
        node.data = data;
        node.rank = 0;
        node.parent = node;
        map.put(data, node);
    }

    public void union(int data1, int data2) {
        int p1 = findSet(data1);
        int p2 = findSet(data2);

        Node parent1 = map.get(p1);
        Node parent2 = map.get(p2);

        if (parent1.rank >= parent2.rank) {
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }

        if (parent1.rank == parent2.rank) {
            parent1.rank++;
        }
    }

    public int findSet(int data) {
        return findParent(map.get(data)).data;
    }

    private Node findParent(Node node) {
        Node parent = node.parent;
        if (parent != node) {
            parent = findParent(parent);
            node.parent = parent;
        }

        return parent;
    }

//    public static void main(String[] args) {
//        DisjointSet ds = new DisjointSet();
//        ds.makeSet(1);
//        ds.makeSet(2);
//        ds.makeSet(3);
//        ds.makeSet(4);
//        ds.makeSet(5);
//        ds.makeSet(6);
//        ds.makeSet(7);
//
//        ds.union(1, 2);
//        ds.union(2, 3);
//        ds.union(4, 5);
//        ds.union(6, 7);
//        ds.union(5, 6);
//        ds.union(3, 7);
//
//        System.out.println(ds.findSet(1));
//        System.out.println(ds.findSet(2));
//        System.out.println(ds.findSet(3));
//        System.out.println(ds.findSet(4));
//        System.out.println(ds.findSet(5));
//        System.out.println(ds.findSet(6));
//        System.out.println(ds.findSet(7));
//
//
//    }
}
