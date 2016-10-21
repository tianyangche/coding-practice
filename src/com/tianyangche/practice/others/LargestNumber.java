package com.tianyangche.practice.others;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by tianyangche on 3/17/16.
 */
public class LargestNumber {
    class MyComparator implements Comparator<String> {
        public int compare(String i, String j) {
            return (i + j).compareTo(j + i);
        }
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }
        StringBuilder sb = new StringBuilder();
        MyComparator myComparator = new MyComparator();
        Arrays.sort(strs, myComparator);

        for (String s : strs) {
            sb.append(s);
        }

        return sb.toString();
    }
}
