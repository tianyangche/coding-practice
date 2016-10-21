package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/15/16.
 */
public class NumMatrix {
    private int m;
    private int n;
    private int[][] sum;
    public NumMatrix(int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.sum = new int[m][n];
        this.sum[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            
        }
    }

}
