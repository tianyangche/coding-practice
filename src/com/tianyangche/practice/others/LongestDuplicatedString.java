package com.tianyangche.practice.others;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * Created by tianyangche on 7/19/16.
 */
public class LongestDuplicatedString {
    public String longestDuplicatedString(String input) {
        if (input == null || input.length() < 2) {
            return "";
        }
        Set<String> set = new HashSet<>();
        int n = input.length();
        for (int len = n - 1; len > 0; len--) {
            set.clear();
            for (int i = 0; i + len <= n; i++) {
                String str = input.substring(i, i + len);
                if (set.contains(str)) {
                    return str;
                }
                set.add(str);
            }
        }
        System.out.println(0 - Integer.MIN_VALUE);
        return "";
    }

    public static void main(String[] args) {
        System.out.println(0 - Integer.MIN_VALUE);
    }
}
