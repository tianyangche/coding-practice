package com.tianyangche.practice.others;

import static javafx.scene.input.KeyCode.M;

/**
 * Created by tianyangche on 4/21/16.
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1.isEmpty() || word2.isEmpty()) {
            return word1.isEmpty() ? word2.length() : word1.length();
        }
        int m = word1.length();
        int n = word2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            f[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            f[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i - 1][j], Math.min(f[i][j - 1], f[i][j])) + 1;
                }
            }
        }

        return f[m][n];
    }
}
