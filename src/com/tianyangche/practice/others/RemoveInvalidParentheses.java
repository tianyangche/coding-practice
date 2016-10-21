package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 3/23/16.
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        // left to right
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        set.add(s);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                if (isValid(str)) {
                    res.add(str);
                }
                queue.offer(str);
            }
            if (res.size() != 0) {
                break;
            }

            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for (int j = 0; j < str.length(); j++) {
                    char c = str.charAt(j);
                    if (c == '(' || c == ')') {

                        String candidate = str.substring(0, j) + str.substring(j + 1);
                        if (!set.contains(candidate)) {
                            queue.offer(candidate);
                            set.add(candidate);
                        }
                    }
                }
            }

        }

        return res;


    }

    private boolean isValid(String s) {
        int pairs = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                pairs++;
            }
            if (c == ')') {
                if (pairs == 0) {
                    return false;
                }
                pairs--;
            }
        }
        return pairs == 0;
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses validator = new RemoveInvalidParentheses();
        String s = "()())()";
        List<String> res = validator.removeInvalidParentheses(s);
        System.out.println(res);
    }
}
