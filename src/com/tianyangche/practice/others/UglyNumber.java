package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/9/16.
 */
public class UglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        int len = primes.length;
        int[] indexes = new int[len];

        for (int i = 2; i <= n; i++) {
            int curr = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                curr = Math.min(curr, primes[j] * res.get(indexes[j]));
            }

            for (int j = 0; j < n; j++) {
                if (curr == primes[j] * res.get(indexes[j])) {
                    indexes[j]++;
                }
            }

            res.add(curr);
        }

        return res.get(res.size() - 1);
    }
}
