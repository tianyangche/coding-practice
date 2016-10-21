package com.tianyangche.practice.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tianyangche on 4/24/16.
 */
public class ValidParentheses {
    private static final Map<Character, Character> MAP = new HashMap<>();
    static {
        MAP.put('(', ')');
        MAP.put('[', ']');
        MAP.put('{', '}');
    }

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || MAP.get(stack.pop()) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
