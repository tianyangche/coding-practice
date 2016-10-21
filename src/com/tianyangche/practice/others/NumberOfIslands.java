package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyangche on 3/5/16.
 */

public class NumberOfIslands {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DisjointSet ds = new DisjointSet();
        List<Integer> res = new ArrayList<Integer>();
        int[] row = {-1, 0, 1, 0};
        int[] col = {0, -1, 0, 1};
        for (int[] pos : positions) {
            ds.makeSet(pos[0] * n + pos[1]);
            int p1;
            int p2;
            for (int k = 0; k < 4; k++) {
                int r = pos[0] + row[k];
                int c = pos[1] + col[k];
                if (r >= 0 && r < m && c >= 0 && c < n && ds.contains(r * n + c)) {
                    p1 = ds.findSet(r);
                    p2 = ds.findSet(c);
                    if (p1 != p2) {
                        ds.union(r, c);
                        ds.count--;
                    }
                }
            }
            res.add(ds.count);
        }

        return res;
    }

    class DisjointSet {

        int count = 0;
        class Node {
            int data;
            int rank;
            Node parent;
        }

        public boolean contains(int data) {
            return map.containsKey(data);
        }

        private Map<Integer, Node> map = new HashMap<>();

        public void makeSet(int data) {
            Node node = new Node();
            node.data = data;
            node.rank = 0;
            node.parent = node;
            map.put(data, node);
            count++;
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
    }

    public static void main(String[] args) {
        NumberOfIslands islands = new NumberOfIslands();
        int[][] input = {{0, 0}, {1, 1}, {0, 1}};

        List<Integer> res = islands.numIslands2(2, 2, input);
        System.out.println(res.toString());
    }
}