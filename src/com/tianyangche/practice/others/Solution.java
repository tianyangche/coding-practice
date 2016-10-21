package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 2/28/16.
 */
public class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return;
        }

        int negativeCount = partitionArray(A, 0);
        if (negativeCount < A.length - negativeCount) {
            swap(A);
        }

        int i = 1;
        int j = A.length % 2 == 0 ? A.length - 1 : A.length - 2;

        while (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i += 2;
            j -= 2;
        }
    }


    private void swap(int[] A) {
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j--;
        }
    }


    private int partitionArray(int[] nums, int k) {
        //write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (i < j && nums[i] < k) {
                i++;
            }
            if (i == nums.length - 1) {
                return nums.length;
            }
            if (i == j) {
                return i;
            }
            while (i < j && nums[j] >= k) {
                j--;
            }
            if (i == j) {
                return j;
            }
            swap(nums, i, j);
        }

        return nums.length;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}