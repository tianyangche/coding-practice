package com.tianyangche.practice.interview.facebook;

/**
 * Created by tianyangche on 6/6/16.
 */
public class BalancedParentheses {
    public String balancedParentheses(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        char[] array = input.toCharArray();
        int left = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                left++;
            } else if (array[i] == ')') {
                if (left == 0) {
                    array[i] = '#';
                } else {
                    left--;
                }
            }
        }

        int right = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == ')') {
                right++;
            } else if (array[i] == '(') {
                if (right == 0) {
                    array[i] = '#';
                } else {
                    right--;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (char c : array) {
            if (c != '#') {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String input = "(abc))((())";
        BalancedParentheses balancedParentheses = new BalancedParentheses();
        System.out.println(balancedParentheses.balancedParentheses(input));
    }
}
