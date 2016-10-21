package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/10/16.
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (first >= num) {
                first = num;
            } else if (second >= num) {
                second = num;
            } else {
                return true;
            }
        }

        return false;
    }
}
