package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 5/10/16.
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            while (right < s.length() && s.charAt(left) == s.charAt(right)) {
                right++;
            }
            i = right + 1;
            while (left > 0 && right < s.length() - 1 && s.charAt(left - 1) == s.charAt(right + 1)) {
                left--;
                right++;
            }
            if (left == 0 && index < right + 1) {
                index = right + 1;
            }
        }

        return new StringBuilder(s.substring(index)).reverse().toString() + s;
    }
}
