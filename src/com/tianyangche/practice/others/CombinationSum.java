package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianyangche on 4/9/16.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(res, new ArrayList<Integer>(), candidates, 0, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> curr, int[] candidates, int index, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            curr.add(candidates[i]);
            dfs(res, curr, candidates, i, target - candidates[i]);
            curr.remove(curr.size() - 1);
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];
        Arrays.fill(visited, false);
        dfs(res, new ArrayList<Integer>(), candidates, 0, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> curr, boolean[] visited, int[] candidates, int index, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i != index && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            curr.add(candidates[i]);
            dfs(res, curr, candidates, i + 1, target - candidates[i]);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), 1, k, n);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> curr, int index, int k, int n) {
        if (n < 0) {
            return;
        }
        if (n == 0) {
            if (curr.size() == k) {
                res.add(new ArrayList<>(curr));
            }
            return;
        }
        for (int i = index; i < 10; i++) {
            if (i > n) {
                break;
            }
            curr.add(i);
            dfs(res, curr, i + 1, k, n - i);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum3(3, 9));
    }
}
