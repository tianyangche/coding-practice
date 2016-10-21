package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 5/9/16.
 */
public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        boolean[][] isPalindrome = makeIsPalindromeMatrix(s);
        List<String> partialList = new ArrayList<String>();
        partitionHelper(res, s, 0, isPalindrome, partialList);
        return res;
    }

    private void partitionHelper(List<List<String>> res, String s, int pos, boolean[][] isPalindrome, List<String> partialList) {
        if (pos == s.length()) {
            if (partialList.size() > 0) {
                res.add(new ArrayList<String>(partialList));
            }
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (isPalindrome[pos][i]) {
                partialList.add(s.substring(pos, i + 1));
                partitionHelper(res, s, i + 1, isPalindrome, partialList);
                partialList.remove(partialList.size() - 1);
            }
        }
    }

    private boolean[][] makeIsPalindromeMatrix(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; i + j < n; j++) {
                isPalindrome[j][i + j] = i == 1 ? s.charAt(j) == s.charAt(j + i) : s.charAt(j) == s.charAt(j + i) && isPalindrome[j + 1][i + j - 1];
            }
        }
        return isPalindrome;
    }

    public static void main(String[] args) {
        PalindromePartition palindromePartition = new PalindromePartition();
        String input = "aab";
        System.out.println(palindromePartition.partition(input));
    }
}
