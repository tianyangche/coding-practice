package com.tianyangche.practice.interview.uber;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by tianyangche on 8/27/16.
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        if (target == 0 || nums == null || nums.length == 0) {
            return 0;
        }

        HashSet set = new HashSet();
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j : nums) {
                if (i < j) {
                    break;
                }
                f[i] += f[i - j];
            }
        }
        return f[target];
    }

    public static void main(String[] args) {
        CombinationSumIV combinationSumIV = new CombinationSumIV();
        int[] nums = {1, 2, 3, 4};

        int target = 4;
        System.out.println(combinationSumIV.combinationSum4(nums, target));
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);
    }
}
