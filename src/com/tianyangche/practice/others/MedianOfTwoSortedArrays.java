package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/24/16.
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 1) {
            return helper(nums1, 0, m, nums2, 0, n, (m + n) / 2);
        } else {
            return (helper(nums1, 0, m, nums2, 0, n, (m + n) / 2) + helper(nums1, 0, m, nums2, 0, n, (m + n) / 2 + 1)) / 2.0;
        }
    }

    private int helper(int[] nums1, int start_1, int len_1, int[] nums2, int start_2, int len_2, int target) {
        if (len_1 > len_2) {
            return helper(nums2, start_2, len_2, nums1, start_1, len_1, target);
        }
        if (target == 0) {
            return Math.min(nums1[0], nums2[0]);
        }

        int aa = Math.min(target / 2, len_1);
        int bb = target - aa;

        if (nums1[start_1 + aa - 1] < nums2[start_2 + bb - 1]) {
            return helper(nums1, start_1 + aa, len_1 - aa, nums2, start_2, len_2, target - aa);
        } else {
            return helper(nums1, start_1, len_1, nums2, start_2 + bb, len_2 - bb, target - bb);
        }
    }
}
