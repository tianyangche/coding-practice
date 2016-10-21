package com.tianyangche.practice.datastructure;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by diwang on 5/13/16.
 */
public class Solution {
    private static final Set<Character> set = new HashSet<>();
    {
        set.add('.');
        set.add('!');
        set.add('?');
    }
    public int solution(String s) {
        int i = 0;
        int j = 0;
        int max = 0;
        while (j < s.length()) {
            char c = s.charAt(j);
            if (set.contains(c)) {
                String sentence = s.substring(i, j);
                String[] words = sentence.split("\\s+");
                max = Math.max(max, words.length);
                i = j + 1;
            }
            j++;
        }
        return max;
    }
}