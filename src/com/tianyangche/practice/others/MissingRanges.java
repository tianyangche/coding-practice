package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/10/16.
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = i == nums.length ? upper : nums[i];
            if (curr - prev >= 2) {
                res.add(makeRange(prev, curr));
            }
            prev = curr;
        }

        return res;
    }
    private String makeRange(int start, int end) {
        return end == start ? String.valueOf(start) : start + "->" + end;
    }

    public static void main(String[] args) {
        MissingRanges missingRanges = new MissingRanges();

    }
}
