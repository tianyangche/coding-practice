package com.tianyangche.practice.others;

import java.util.Arrays;

/**
 * Created by tianyangche on 4/18/16.
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            max = Math.max(i, max);
            min = Math.min(i, min);
        }
        int len = (max - min) / nums.length + 1;
        int numOfBuckets = (max - min) / len + 1;
        int[] bucketMin = new int[numOfBuckets];
        int[] bucketMax = new int[numOfBuckets];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for (int i = 0; i < nums.length; i++) {
            int index = (nums[i] - min) / len;
            bucketMin[index] = Math.min(nums[i], bucketMin[index]);
            bucketMax[index] = Math.max(nums[i], bucketMax[index]);
        }

        int res = 0;
        int prev = 0;
        for (int i = 1; i < numOfBuckets; i++) {
            if (bucketMax[i] == Integer.MIN_VALUE && bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }
            res = Math.max(res, bucketMin[i] - bucketMax[prev]);
            prev = i;
        }
        return res;
    }
}
