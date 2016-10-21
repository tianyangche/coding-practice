package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 4/10/16.
 */
public class Permutation {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                count++;
            }
            if (count >= 2) {
                return res;
            }
        }

        char c = 'a';
        StringBuilder input = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                c = entry.getKey();
            }
            for (int j = 1; j <= entry.getValue() / 2; j++) {
                input.append(entry.getKey());
            }
        }

        char[] inputs = input.toString().toCharArray();

        List<String> tempRes = new ArrayList<>();
        List<Character> curr = new ArrayList<Character>();

        boolean[] visited = new boolean[inputs.length];
        Arrays.fill(visited, false);
        dfs(inputs, curr, tempRes, visited);

        for (String str : tempRes) {
            if (s.length() % 2 == 1) {
                res.add(str + c + new StringBuilder(str).reverse().toString());
            } else {
                res.add(str + new StringBuilder(str).reverse().toString());
            }
        }

        return res;

    }

    private void dfs(char[] nums, List<Character> curr, List<String> res, boolean[] visited) {
        if (curr.size() == nums.length ) {
            res.add(new String(convert(curr)));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            curr.add(nums[i]);
            visited[i] = true;

            dfs(nums, curr, res, visited);

            visited[i] = false;
            curr.remove(curr.size() - 1);
        }
    }

    private String convert(List<Character> curr) {
        StringBuilder builder = new StringBuilder();
        for (char c : curr) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        String s = "aaBB//a";
        List<String> res = permutation.generatePalindromes(s);
        System.out.println(res);
    }
}
