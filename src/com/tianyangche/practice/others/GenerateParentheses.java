package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/9/16.
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs(res, n, n, "");
        return res;
    }

    private void dfs(List<String> list, int leftRemaining, int rightRemaining, String curr) {
        if (leftRemaining == 0 && rightRemaining == 0) {
            list.add(curr);
            return;
        }
        if (leftRemaining > rightRemaining) {
            return;
        }
        if (leftRemaining > 0) {
            dfs(list, leftRemaining - 1, rightRemaining, curr + "(");
        }
        if (rightRemaining > 0) {
            dfs(list, leftRemaining, rightRemaining - 1, curr + ")");
        }
    }

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(2));
    }
}
