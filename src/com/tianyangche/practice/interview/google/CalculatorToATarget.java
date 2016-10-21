package com.tianyangche.practice.interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianyangche on 6/18/16.
 */
public class CalculatorToATarget {
    public static boolean canFind(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        if (numbers.length == 0) {
            return numbers[0] == target;
        }
        Arrays.sort(numbers);
        int[] benchmark = new int[numbers.length];
        System.arraycopy(numbers, 0, benchmark, 0, numbers.length);
        do {
            for (int i = 0; i < numbers.length - 1; i++) {
                List<Integer> leftRes = dfs(numbers, 0, i);
                List<Integer> rightRes = dfs(numbers, i + 1, numbers.length - 1);
                for (int l : leftRes) {
                    for (int r : rightRes) {
                        if (l + r == target) {
                            return true;
                        }
                        if (l - r == target) {
                            return true;
                        }
                        if (l * r == target) {
                            return true;
                        }
                        if (r != 0 && l / r == target) {
                            return true;
                        }
                    }
                }
            }
            nextPermutation(numbers);
        } while (!isSame(numbers, benchmark));

        return false;
    }

    public static void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        swap(nums, i, j);
                        reverse(nums, i + 1, nums.length - 1);
                        return;
                    }
                }
            }
        }
        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] nums, int m, int n) {
        while (m < n) {
            swap(nums, m, n);
            m++;
            n--;
        }
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static boolean isSame(int[] numbers, int[] benchmark) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != benchmark[i]) {
                return false;
            }
        }
        return true;
    }
    private static List<Integer> dfs(int[] numbers, int start, int end) {
        List<Integer> res = new ArrayList<>();
        if (start > end) {
            return res;
        }
        if (start == end) {
            res.add(numbers[start]);
            return res;
        }

        for (int i = start; i < end; i++) {
            List<Integer> leftRes = dfs(numbers, start, i);
            List<Integer> rightRes = dfs(numbers, i + 1, end);
            for (int l : leftRes) {
                for (int r : rightRes) {
                    res.add(l + r);
                    res.add(l - r);
                    res.add(l * r);
                    if (r != 0) {
                        res.add(l / r);
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 5, 9, 11};
        int target = 24;
        System.out.println(canFind(numbers, target));
    }
}
