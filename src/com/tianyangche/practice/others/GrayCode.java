package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/9/16.
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        res.add(0);
        for (int i = 1; i <= n; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) + (1 << (i - 1)));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GrayCode code = new GrayCode();
        System.out.println(code.grayCode(4));
    }
}
