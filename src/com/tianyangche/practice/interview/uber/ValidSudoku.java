package com.tianyangche.practice.interview.uber;

/**
 * Created by tianyangche on 8/27/16.
 */
public class ValidSudoku {
    public static final int N = 9;
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int[] row = new int[N];
        int[] col = new int[N];
        int[] block = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int offset = (int)(board[i][j] - '1');
                int tmp = 1 << offset;
                if ((row[i] & tmp) != 0 || (col[j] & tmp) != 0 || (block[i / 3 * 3 + j / 3]) != 0) {
                    return false;
                }
                row[i] &= tmp;
                col[j] &= tmp;
                block[i / 3 * 3 + j / 3] &= tmp;
            }
        }

        return true;
    }
}
