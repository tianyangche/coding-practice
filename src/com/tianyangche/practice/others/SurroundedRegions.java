package com.tianyangche.practice.others;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tianyangche on 3/28/16.
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.offer(i * n);

            }
            if (board[i][n - 1] == 'O') {
                queue.offer(i * n + n - 1);
                System.out.println("yes");
            }
        }

        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                queue.offer(i);
            }
            if (board[m - 1][i] == 'O') {
                queue.offer((m - 1) * n + i);
                System.out.println("yes");
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.peek() / n;
            int j = queue.poll() % n;
            board[i][j] = '#';
            if (i - 1 >= 0 && board[i - 1][j] == 'O') {
                queue.offer((i - 1) * n + j);
            }
            if (i + 1 < m && board[i + 1][j] == 'O') {
                queue.offer((i + 1) * n + j);
            }
            if (j - 1 >= 0 && board[i][j - 1] == 'O') {
                queue.offer(i * n + j - 1);
            }
            if (j + 1 < n && board[i][j + 1] == 'O') {
                queue.offer(i * n + j + 1);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions regions = new SurroundedRegions();

        char[][] input = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };

        regions.solve(input);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
