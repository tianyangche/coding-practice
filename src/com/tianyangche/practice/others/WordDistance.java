package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyangche on 4/1/16.
 */
public class WordDistance {
    private Map<String, List<Integer>> map;

    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (map.containsKey(word)) {
                map.get(word).add(i);
            } else {
                map.put(word, new ArrayList<>());
                map.get(word).add(i);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> posList1 = map.get(word1);
        List<Integer> posList2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        for (int i : posList1) {
            for (int j : posList2) {
                res = Math.min(res, Math.abs(i - j));
            }
        }

        return res;
    }
}