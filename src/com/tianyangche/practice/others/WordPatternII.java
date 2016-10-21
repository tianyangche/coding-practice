package com.tianyangche.practice.others;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianyangche on 5/7/16.
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.isEmpty() && str.isEmpty()) {
            return true;
        }
        if (pattern.length() > str.length()) {
            return false;
        }
        Map<Character, String> firstToSecond = new HashMap<>();
        Map<String, Character> secondToFirst = new HashMap<>();
        return wordPatternMatchHelper(pattern, str, 0, 0, firstToSecond, secondToFirst);
    }

    private boolean wordPatternMatchHelper(String pattern, String str, int p, int q, Map<Character, String> firstToSecond, Map<String, Character> secondToFirst) {
        if (p == pattern.length()) {
            return str.length() == q;
        }
        char c = pattern.charAt(p);
        while (p != pattern.length() && firstToSecond.containsKey(c)) {
            c = pattern.charAt(p);
            String temp = firstToSecond.get(c);
            if (q + temp.length() >= str.length() || !str.substring(q, q + temp.length()).equals(temp)) {
                return false;
            }
            q += temp.length();
            p++;
        }

        if (p == pattern.length()) {
            return q == str.length();
        }

        c = pattern.charAt(p);
        for (int i = q; i < str.length(); i++) {
            String temp = str.substring(q, i + 1);
            if (secondToFirst.containsKey(temp) && !secondToFirst.get(temp).equals(c)) {
                continue;
            }
            firstToSecond.put(c, temp);
            secondToFirst.put(temp, c);
            if (wordPatternMatchHelper(pattern, str, p + 1, q + 1, firstToSecond, secondToFirst)) {
                return true;
            }
            firstToSecond.remove(c);
            secondToFirst.remove(temp);
        }
        return false;
    }
}
