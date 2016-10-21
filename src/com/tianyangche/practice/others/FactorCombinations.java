package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/9/16.
 */
public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        if (n == 1) {
            return res;
        }
        dfs(res, curr, 2, n);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> curr, int factor, int n) {
        if (n == 1) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = factor; i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            curr.add(i);
            dfs(res, curr, i, n / i);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        FactorCombinations factorCombinations = new FactorCombinations();
        System.out.println(factorCombinations.getFactors(16));
    }
}
