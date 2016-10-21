package com.tianyangche.practice.others;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianyangche on 4/2/16.
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        if (n <= 1) {
            return true;
        }
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int res = compute(n);
            if (set.contains(res)) {
                return false;
            }
            set.add(res);
            n = res;
        }
        return true;
    }

    private int compute(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "\t" + happyNumber.isHappy(i));
        }
    }
}
