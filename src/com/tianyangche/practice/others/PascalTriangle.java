package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/21/16.
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        res.add(temp);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> curr = res.get(res.size() - 1);
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            for (int j = 2; j < i; j++) {
                newList.add(curr.get(j - 1) + curr.get(j - 2));
            }
            newList.add(1);
            res.add(newList);
        }
        return res;
    }
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex == 0) {
            return res;
        }
        List<Integer> curr = new ArrayList<>();
        curr.add(1);
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i - 2; j > 0; j--) {
                curr.set(j, curr.get(j) + curr.get(j - 1));
            }
            curr.add(1);
        }
        return curr;
    }
    public static void main(String[] args) {
        PascalTriangle triangle = new PascalTriangle();
        System.out.println(triangle.getRow(3));
    }
}
