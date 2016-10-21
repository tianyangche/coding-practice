package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianyangche on 5/29/16.
 */
public class ZigzagIterator {
    List<Integer> l1;
    List<Integer> l2;
    int i1;
    int i2;
    int index;
    int choose;
    int capacity;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        l1 = v1 == null ? new ArrayList<Integer>() : v1;
        l2 = v2 == null ? new ArrayList<Integer>() : v2;
        i1 = 0;
        i2 = 0;
        index = 0;
        choose = 0;
        capacity = v1.size() + v2.size();
    }

    public int next() {
        int res = 0;
        if (choose == 0) {
            if (i1 == l1.size()) {
                res = l2.get(i2++);
            } else {
                res = l1.get(i1++);
            }
            choose = i2 >= l2.size() ? 0 : 1;
        } else {
            if (i2 == l2.size()) {
                res = l1.get(i1++);
            } else {
                res = l2.get(i2++);
            }
            choose = i1 >= l1.size() ? 1 : 0;
        }
        index++;
        return res;
    }

    public boolean hasNext() {
        return index < capacity;
    }

    public static void main(String[] args) {
        Integer[] first = {1,2};
        Integer[] second = {3,4,5,6};
        List<Integer> v1 = Arrays.asList(first);
        List<Integer> v2 = Arrays.asList(second);

        ZigzagIterator iterator = new ZigzagIterator(v1, v2);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }

    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */