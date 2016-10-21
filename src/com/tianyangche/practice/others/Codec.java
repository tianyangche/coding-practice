package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/10/16.
 */
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder builder = new StringBuilder();
        for (String s : strs) {
            if (s == null || s.isEmpty()) {
                builder.append("0#");
            } else {
                builder.append(s.length()).append('#').append(s);
            }
        }
        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        for (int i = 0; i < s.length();) {
            int start = i;
            while (i < s.length() && s.charAt(i) != '#') {
                i++;
            }
            int len = Integer.valueOf(s.substring(start, i));
            System.out.println(i);
            res.add(s.substring(i + 1, i + len + 1));
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        List<String> strs = new ArrayList<String>();
        strs.add("0");
        System.out.println(codec.decode(codec.encode(strs)));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
