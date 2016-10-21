package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/12/16.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            f[i][0] = matrix[i][0] - '0';
        }
        for (int i = 0; i < m; i++) {
            f[0][i] = matrix[0][i] - '0';
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    f[i][j] = 0;
                } else {
                    int l1 = f[i - 1][j];
                    int l2 = f[i][j - 1];
                    if (l1 != l2) {
                        f[i][j] = Math.min(l1, l2) + 1;
                    } else {
                        f[i][j] = matrix[i - l1][j - l1] == '0' ? l1 : l1 + 1;
                    }
                }
            }
        }

        int max = 0;
        for (int[] row : f) {
            for (int col : row) {
                max = Math.max(max, col);
            }
        }
        return max * max;
    }
}
