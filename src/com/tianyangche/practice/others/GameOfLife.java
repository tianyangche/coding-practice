package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/10/16.
 */
public class GameOfLife {
    private static final int[][] offset = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = computeLivesAround(board, i, j);
                if (board[i][j] == 0) {
                    if (count == 3) {
                        board[i][j] = 2;
                    }
                } else {
                    if (count < 2) {
                        board[i][j] = -1;
                    } else if (count == 2 || count == 3) {
                        board[i][j] = 1;
                    } else if (count > 3) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int computeLivesAround(int[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int[] entry : offset) {
            if ((i + entry[0] >= 0 && i + entry[0] < m) && (j + entry[1] >= 0 && j + entry[1] < n)) {
                if (Math.abs(board[i][j]) == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
