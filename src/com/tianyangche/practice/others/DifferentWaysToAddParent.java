package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 5/13/16.
 */
public class DifferentWaysToAddParent {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input == null || input.isEmpty()) {
            return res;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftRes = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightRes = diffWaysToCompute(input.substring(i + 1));
                for (int l : leftRes) {
                    for (int r : rightRes) {
                        if (c == '+') {
                            res.add(l + r);
                        } else if (c == '-') {
                            res.add(l - r);
                        } else {
                            res.add(l * r);
                        }
                    }
                }
            }
        }
        return res;
    }
}
