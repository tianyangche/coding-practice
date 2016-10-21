package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by tianyangche on 3/8/16.
 */
public class SlidingWindow {
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (k == 1) {
            res.addAll(new ArrayList(Arrays.asList(nums)));
            return res;
        }


        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] > deque.getFirst()) {
                deque.removeFirst();
            }
            deque.addFirst(nums[i]);
            if (i >= k - 1) {
                res.add(deque.getLast());
            }
        }
        return res;

    }

    public static void main(String[] args) {
        SlidingWindow window = new SlidingWindow();
        int[] nums = {1, 2, 7, 7, 8};
        System.out.println(window.maxSlidingWindow(nums, 3));
    }
}
