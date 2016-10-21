package com.tianyangche.practice.others;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by tianyangche on 4/18/16.
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        int n = nums.length;
        for (; index < k; index++) {
            while(!deque.isEmpty() && nums[deque.getLast()] <= nums[index]) {
                deque.removeLast();
            }
            deque.addLast(index);
        }
        int[] res = new int[n - k + 1];
        for (; index < n; index++) {
            res[index - k] = nums[deque.getFirst()];
            while (!deque.isEmpty() && deque.getFirst() < index - k + 1) {
                deque.removeFirst();
            }
            while(!deque.isEmpty() && nums[deque.getLast()] <= nums[index]) {
                deque.removeLast();
            }
            deque.addLast(index);
        }
        res[n - k] = nums[deque.getFirst()];

        return res;
    }
}
