package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/12/16.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || board.length * board[0].length < word.length()) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, visited, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int pos, boolean[][] visited, int i, int j) {
        if (pos == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(pos)) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (dfs(board, word, pos + 1, visited, i - 1, j)) {
            return true;
        }
        if (dfs(board, word, pos + 1, visited, i, j - 1)) {
            return true;
        }
        if (dfs(board, word, pos + 1, visited, i + 1, j)) {
            return true;
        }
        if (dfs(board, word, pos + 1, visited, i, j + 1)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

}
