package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 4/2/16.
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strings) {
            if (str.isEmpty()) {
                continue;
            }
            String hash = getKey(str);
            if (map.containsKey(hash)) {
                map.get(hash).add(str);
            } else {
                map.put(hash, new ArrayList<>());
                map.get(hash).add(str);
            }
        }

        for (List<String> list : map.values()) {
            Collections.sort(list);
            res.add(list);
        }

        return res;
    }

    private String getKey(String input) {
        StringBuilder sb = new StringBuilder();
        int delta = input.charAt(0) - 'a';
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i) - delta < 'a' ? (char) (input.charAt(i) - delta + 26) : (char)(input.charAt(i) - delta);

            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
