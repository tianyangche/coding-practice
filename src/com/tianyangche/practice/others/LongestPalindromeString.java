package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 5/9/16.
 */
public class LongestPalindromeString {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }
        int max = 1;
        int start = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int[] res = findLongestPalindrome(chars, i, i);
            if (res[1] - res[0] + 1 > max) {
                max = res[1] - res[0] + 1;
                start = res[0];
            }
            res = findLongestPalindrome(chars, i, i + 1);
            if (res[1] - res[0] + 1 > max) {
                max = res[1] - res[0] + 1;
                start = res[0];
            }
        }

        return s.substring(start, start + max);
    }

    private int[] findLongestPalindrome(char[] chars, int i, int j) {
        int[] res = {-1, -1};
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        res[0] = i + 1;
        res[1] = j - 1;

        return res;
    }

    public static void main(String[] args) {
        String s = "abba";
        LongestPalindromeString longestPalindromeString = new LongestPalindromeString();
        System.out.println(longestPalindromeString.longestPalindrome(s));
    }
}
