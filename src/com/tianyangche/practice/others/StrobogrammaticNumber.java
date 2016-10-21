package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 4/2/16.
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        if (num == null || num.isEmpty() || num.length() == 1) {
            return true;
        }
        int length = num.length();
        for (int i = 0; i < length / 2; i++) {
            char c = num.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            if (map.get(c) != num.charAt(num.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        Queue<String> queue = new LinkedList<>();
        if (n % 2 != 1) {
            queue.offer("");
        } else {
            queue.offer("0");
            queue.offer("1");
            queue.offer("8");
            n--;
        }
        while (n != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (Character c : map.keySet()) {
                    queue.offer(c + curr + map.get(c));
                }
            }
            n -= 2;
        }

        List<String> res = new LinkedList<>();
        res = (LinkedList<String>) queue;
        return res;
    }
    public static void main(String[] args) {

    }
}
