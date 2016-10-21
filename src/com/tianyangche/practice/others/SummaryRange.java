package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/12/16.
 */
public class SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res.add(generateRange(nums[0], nums[0]));
            return res;
        }

        int start = 0;
        int end = 1;
        while (end < nums.length) {
            while (end < nums.length && nums[end] - nums[end - 1] == 1) {
                end++;
            }
            res.add(generateRange(nums[start], nums[end - 1]));
            start = end;
            end = start + 1;
        }
        if (start != nums.length) {
            res.add(generateRange(nums[start], nums[nums.length - 1]));
        }
        return res;
    }
    private String generateRange(int start, int end) {
        return start == end ? new StringBuilder().append(start).toString() : new StringBuilder().append(start).append("->").append(end).toString();
    }

    public static void main(String[] args) {
        SummaryRange summaryRange = new SummaryRange();
        int[] nums = {0,1,2,3, 4,5,6, 7};
        System.out.println(summaryRange.summaryRanges(nums));
    }
}
