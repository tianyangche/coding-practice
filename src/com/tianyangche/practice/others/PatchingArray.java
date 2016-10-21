package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianyangche on 4/11/16.
 */
public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        boolean[] elements = new boolean[n];
        Arrays.fill(elements, false);
        int count = 0;
        List<Integer> currArray = subsets(nums);
        for (int i = 0; i < currArray.size(); i++) {
            elements[currArray.get(i) - 1] = true;
            count++;
        }
        int added = 0;
        if (count == n) {
            return added;
        }
//        while (count != n) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == false) {
                    added++;
                    int temp = i;
                    elements[i] = true;
                    for (int j = n - 1; j >= 0; j--) {
                        if (j + i < n && elements[j] && !elements[j + i]) {
                            elements[j + i] = true;
                            count++;
                        }
                    }
                    if (count == n) {
                        break;
                    }
                }
            }
//        }

        for (int i = 0; i < n; i++) {
            if (!elements[i]) {
               // System.out.print(i + 1 + "\t");
            }
        }
        return added;
    }

    public List<Integer> subsets(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        List<Integer> curr = new ArrayList<Integer>();
        dfs(nums, curr, res, 0, 0);
        return res;
    }

    private void dfs(int[] nums, List<Integer> curr, List<Integer> res, int pos, int currSum) {
        if (pos == nums.length) {
            if (currSum != 0) {
                res.add(currSum);
            }
            return;
        }
        dfs(nums, curr, res, pos + 1, currSum + nums[pos]);
        dfs(nums, curr, res, pos + 1, currSum);
    }

    public static void main(String[] args) {
        PatchingArray pa = new PatchingArray();
        int[] array = {1, 2, 31, 33};
        int n = Integer.MAX_VALUE;
        System.out.println(pa.minPatches(array, n));
    }
}
