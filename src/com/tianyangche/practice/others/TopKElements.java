package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 5/9/16.
 */
public class TopKElements {
    class EntryComparator implements Comparator<Map.Entry<Integer, Integer>> {
        public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
            if (!entry1.getValue().equals(entry2.getValue())) {
                return entry2.getValue().compareTo(entry1.getValue());
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        if (k == 0 || nums == null || nums.length == 0) {
            return res;
        }
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue(k, new EntryComparator());
        for (Map.Entry entry : map.entrySet()) {
            heap.offer(entry);
        }

        while (k > 0 && !heap.isEmpty()) {
            res.add(heap.poll().getKey());
            k--;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 2, 2, 2, 3};
        int k = 2;
        TopKElements topKElements = new TopKElements();
        System.out.println(topKElements.topKFrequent(nums, k));
    }
}
