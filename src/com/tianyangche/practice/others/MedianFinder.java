package com.tianyangche.practice.others;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by tianyangche on 3/4/16.
 */


public class MedianFinder {
    PriorityQueue<Integer> left = new PriorityQueue<Integer>(10, new MaxComparator());
    PriorityQueue<Integer> right = new PriorityQueue<Integer>(10, new MinComparator());
    int size = 0;
    // Adds a number into the data structure.
    public void addNum(int num) {
        size++;
        if (size <= 2) {
            left.offer(num);
        } else {
            if (num <= left.peek()) {
                left.offer(num);
            } else if (num > left.peek() && num <= right.peek()) {
                right.offer(num);
            } else {
                right.offer(num);
            }
        }




        while (Math.abs(left.size() - right.size()) >= 2) {
            if (left.size() > right.size()) {
                right.offer(left.poll());
            } else {
                left.offer(right.poll());
            }
        }
        System.out.println(left.peek() + "\t" + right.peek());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (size % 2 == 1) {
            return left.size() > right.size() ? left.peek() : right.peek();
        } else {
            return (left.peek() + right.peek()) / 2.0;
        }
    }

    class MaxComparator implements Comparator<Integer> {
        public int compare(Integer i, Integer j) {
            return j.compareTo(i);
        }
    }

    class MinComparator implements Comparator<Integer> {
        public int compare(Integer i, Integer j) {
            return i.compareTo(j);
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
};


// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();