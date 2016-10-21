package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/27/16.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int first = num1.charAt(i) - '0';
                int second = num2.charAt(j) - '0';
                res[i + j + 1] += first * second;
            }
        }
        for (int i = 0; i < m + n - 1; i++) {
            res[i + 1] += res[i] / 10;
            res[i] %= 10;
        }
        StringBuilder builder = new StringBuilder();
        int i = res[m + n - 1] == 0 ? m + n - 2 : m + n - 1;
        while (i >= 0) {
            builder.append(res[i--]);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String first = "123";
        String second = "456";
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply(first, second));
    }
}
