package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/21/16.
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] balloons = new int[n + 2];
        for (int i = 0; i < nums.length; i++) {
            balloons[i + 1] = nums[i];
        }
        balloons[0] = balloons[n - 1] = 1;
        int[][] f = new int[n][n];
        for (int k = 2; k < n; k++) {
            for (int left = 0; left + k < n; left++) {
                int right = left + k;
                for (int m = left + 1; m < right; m ++) {
                    f[left][right] = Math.max(f[left][right], balloons[left] * balloons[m] * balloons[right] + f[left][m] + f[m][right]);
                }
            }
        }
        return f[0][n - 1];
    }
}
