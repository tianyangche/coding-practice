package com.tianyangche.practice.others;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tianyangche on 4/24/16.
 */
public class LettersCombination {
    private static final String[] TABLE = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        for (int i = 0; i < digits.length(); i++) {
            String button = TABLE[digits.charAt(i) - '0'];
            if (button.equals("")) {
                continue;
            }
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String curr = queue.poll();
                for (int m = 0; m < button.length(); m++) {
                    queue.offer(curr + button.charAt(m));
                }
            }
        }
        return (LinkedList)queue;
    }

    public static void main(String[] args) {
        String input = "233";
        LettersCombination combination = new LettersCombination();
        System.out.println(combination.letterCombinations(input));
    }
}
