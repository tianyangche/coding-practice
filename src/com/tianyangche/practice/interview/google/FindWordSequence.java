package com.tianyangche.practice.interview.google;

import java.util.*;

/**
 * Created by tianyangche on 9/7/16.
 */
public class FindWordSequence {
    public List<List<String>> wordSqaure(List<String> words, int length) {
        List<List<String>> res = new ArrayList<>();
        if (words.size() < length) {
            return res;
        }
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });
        List<String> candidates = new ArrayList<>();
        for (String str : words) {
            if (str.length() <= length) {
                candidates.add(str);
            }
        }
        if (candidates.size() < length) {
            return res;
        }
        boolean[] visited = new boolean[candidates.size()];
        helper(res, new ArrayList<>(), candidates, visited, length);

        return res;
    }

    private void helper(List<List<String>> res, List<String> curr, List<String> candidates, boolean[] visited, int length) {
        if (curr.size() == length) {
            if (isSolution(curr, length)) {
                res.add(new ArrayList<String>(curr));
            }
            return;
        }

        for (int i = 0; i < candidates.size(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            curr.add(candidates.get(i));
            helper(res, curr, candidates, visited, length);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }

    private boolean isSolution(List<String> curr, int length) {
        if (curr.get(0).length() < length) {
            return false;
        }
        char[][] chars = new char[length][length];
        for (char[] array : chars) {
            Arrays.fill(array, '*');
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < curr.get(i).length(); j++) {
                chars[i][j] = curr.get(i).charAt(j);
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (chars[i][j] != chars[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FindWordSequence findWordSequence = new FindWordSequence();
        List<String> input = new ArrayList<>();

        input.add("BNRT");
        input.add("ABCD");
        input.add("CRM");
        input.add("DT");
        input.add("DOG");
        input.add("CAT");
        int length = 4;
        List<List<String>> res = findWordSequence.wordSqaure(input, length);
        System.out.println(res);
    }
}
