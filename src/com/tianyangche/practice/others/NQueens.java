package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 5/19/16.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        int[] rows = new int[n];
        solveNQueensHelper(res, rows, 0);
        return res;
    }

    private void solveNQueensHelper(List<List<String>> res, int[] rows, int curr) {
        if (curr == rows.length) {
            res.add(convertToString(rows));
            return;
        }
        for (int i = 0; i < rows.length; i++) {
            rows[curr] = i;
            boolean goDeeper = true;
            for (int j = 0; j < curr; j++) {
                if (rows[j] == rows[curr] || Math.abs(j - curr) == Math.abs(rows[j] - rows[curr])) {
                    goDeeper = false;
                    break;
                }

            }
            if (goDeeper)
            solveNQueensHelper(res, rows, curr + 1);
        }
    }

    private List<String> convertToString(int[] rows) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < rows.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < rows[i]; j++) {
                builder.append('.');
            }
            builder.append('Q');
            for (int j = rows[i] + 1; j < rows.length; j++) {
                builder.append('.');
            }
            res.add(builder.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.solveNQueens(4));
    }
}
