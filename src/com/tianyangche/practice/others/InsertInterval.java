package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 3/18/16.
 */
public class InsertInterval {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        List<Interval> res = new ArrayList<Interval>();
        for (; i < intervals.size(); i++) {
            if (newInterval.end < intervals.get(i).start) {
                res.add(new Interval(newInterval.start, newInterval.end));
                break;
            } else if (newInterval.start > intervals.get(i).end) {
                res.add(intervals.get(i));
                i++;
            } else {
                System.out.println("4");
                newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
                newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
                i++;
            }
        }
        System.out.println(intervals);
        if (i == intervals.size()) {

            res.add(newInterval);
        }
        while (i < intervals.size()) {
            res.add(intervals.get(i++));
        }

        return res;
    }

    public List<Interval> test() {
        List<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(1, 5));

        Interval newInterval = new Interval(2, 3);
        List<Interval> res = new ArrayList<Interval>();

        return insert(intervals, newInterval);
    }

    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();
        List<Interval> res = solution.test();
        System.out.println(res.size());
    }
}

