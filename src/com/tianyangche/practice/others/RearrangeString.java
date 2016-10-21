package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 7/17/16.
 */
public class RearrangeString {
    class EntryComparator implements Comparator<Map.Entry<Character, Integer>> {
        public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
            if (!e1.getValue().equals(e2.getValue())) {
                return e2.getValue() - e1.getValue();
            } else {
                return (int)e1.getKey() - (int)e2.getKey();
            }
        }
    }
    public String rearrangeString(String str, int k) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!counts.containsKey(c)) {
                counts.put(c, 1);
            } else {
                counts.put(c, counts.get(c) + 1);
            }
        }

        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(1, new EntryComparator());
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            heap.offer(entry);
        }
        Map<Character, Integer> lastPositions = new HashMap<>();

        StringBuilder builder = new StringBuilder();

        while (!heap.isEmpty()) {
            List<Character> currTrip = new ArrayList<>();
            while (!heap.isEmpty() && currTrip.size() < k) {
                Map.Entry<Character, Integer> entry = heap.poll();
                builder.append(entry.getKey());
                currTrip.add(entry.getKey());
            }
            for (char c : currTrip) {
                counts.put(c, counts.get(c) - 1);
                if (counts.get(c) == 0) {
                    counts.remove(c);
                }
            }
            heap.clear();
            for (Map.Entry<Character, Integer> entry : counts.entrySet()) {

                heap.offer(entry);
            }
            if (currTrip.size() < k && !counts.isEmpty()) {
                return "";
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String input = "aaabc";
        int k = 2;
        RearrangeString rearrangeString = new RearrangeString();
        System.out.println(rearrangeString.rearrangeString(input, k));
    }
}
