package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 3/28/16.
 */
public class MinimumHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<Integer>();
        if (n == 0) {
            return res;
        }
        if (n == 1) {
            res.add(0);
            return res;
        }
        if (n == 2) {
            res.add(0);
            res.add(1);
            return res;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                queue.offer(entry.getKey());
            }
        }

        while (n > 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int start = queue.poll();
                for (int end : map.get(start)) {
                    map.get(end).remove(start);
                    if (map.get(end).size() == 1) {
                        queue.offer(end);
                    }
                }
                n--;
            }
        }

        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;

    }
}
