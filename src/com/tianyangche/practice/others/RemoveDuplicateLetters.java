package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 4/19/16.
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                if (deque.isEmpty()) {
                    set.add(c);
                    deque.addFirst(c);
                } else {
                    while (!deque.isEmpty() && deque.getFirst() >= c && map.get(deque.getFirst()) > 0) {
                        set.remove(deque.getFirst());
                        deque.removeFirst();
                    }
                    deque.addFirst(c);
                    set.add(c);
                }
            }
            map.put(c, map.get(c) - 1);
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.getLast());
            deque.removeLast();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        String input = "cbacdcbc";
        System.out.println(removeDuplicateLetters.removeDuplicateLetters(input));
    }
}
