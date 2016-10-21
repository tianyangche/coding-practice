package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tianyangche on 5/2/16.
 */
public class WordBreakII {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        int n = s.length();
        boolean[] f = new boolean[n];
        f[0] = wordDict.contains(s.substring(0, 1));
        for (int i = 1; i < n; i++) {
            if (wordDict.contains(s.substring(0, i + 1))) {
                f[i] = true;
            } else {
                for (int j = i; j >= 1; j--) {
                    if (wordDict.contains(s.substring(j, i + 1)) && f[j - 1]) {
                        f[i] = true;
                        break;
                    }
                }
            }
        }
        if (!f[n - 1]) {
            return res;
        }
        dfs(res, s, 0, new ArrayList<String>(), wordDict);
        return res;
    }

    private void dfs(List<String> res, String s, int pos, List<String> curr, Set<String> wordDict) {
        if (pos == s.length()) {
            if (curr.size() != 0) {
                res.add(convert(curr));
            }
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (wordDict.contains(s.substring(pos, i + 1))) {
                curr.add(s.substring(pos, i + 1));
                dfs(res, s, i + 1, curr, wordDict);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private String convert(List<String> curr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < curr.size() - 1; i++) {
            builder.append(curr.get(i)).append(" ");
        }
        builder.append(curr.get(curr.size() - 1));

        return builder.toString();
    }

    public static void main(String[] args) {
        String input = "catsanddog";
        Set<String> set = new HashSet<>();
        set.add("cat");
        set.add("cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        WordBreakII wordBreakII = new WordBreakII();
        System.out.println(wordBreakII.wordBreak(input, set));
    }
}
