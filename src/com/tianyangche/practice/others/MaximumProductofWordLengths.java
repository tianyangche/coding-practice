package com.tianyangche.practice.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MaximumProductofWordLengths {
    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return Integer.compare(str2.length(), str1.length());
        }
    }
    public int maxProduct(String[] words) {
        if (words == null || words.length < 2) {
            return 0;
        }
        int res = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], hash(words[i]));
        }
        Arrays.sort(words, new MyComparator());
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((map.get(words[i]) & map.get(words[j])) == 0) {
                    return words[i].length() * words[j].length();
                }
            }
        }
        return res;
    }

    private int hash(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash |= (1 << (input.charAt(i) - 'a'));
        }
        return hash;
    }

    public static void main(String[] args) {
        MaximumProductofWordLengths lengths = new MaximumProductofWordLengths();
//        String[] strs = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        String[] strs = {"abcw", "xtfn"};
        System.out.println(lengths.maxProduct(strs));

    }
}
