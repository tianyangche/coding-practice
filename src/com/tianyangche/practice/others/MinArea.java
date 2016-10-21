package com.tianyangche.practice.others;

import java.util.Arrays;

/**
 * Created by tianyangche on 3/15/16.
 */
public class MinArea {
    class Node {
        int up;
        int down;
        int left;
        int right;
    }
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0) {
            return 0;
        }
        int m = image.length;
        int n = image[0].length;
        Node res = new Node();
        res.up = Integer.MAX_VALUE;
        res.left = Integer.MAX_VALUE;
        res.down = Integer.MIN_VALUE;
        res.right = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[m][n];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }
        helper(image, x, y, res, visited);
        return (res.right - res.left + 1) * (res.down - res.up + 1);
    }

    private void helper(char[][] image, int x, int y, Node res, boolean[][] visited) {
        if (x < 0) {
            res.up = Math.min(res.up, 0);
            return;
        }
        if (x >= image.length) {
            res.down = Math.max(res.down, image.length - 1);
            return;
        }

        if (y < 0) {
            res.left = Math.min(res.left, 0);
            return;
        }

        if (y >= image[0].length) {
            res.right = Math.max(res.right, image[0].length - 1);
            return;
        }

        if (image[x][y] == '0' || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        res.up = Math.min(res.up, x);
        res.down = Math.max(res.down, x);
        res.left = Math.min(res.left, y);
        res.right = Math.max(res.right, y);
        helper(image, x, y - 1, res, visited);
        helper(image, x - 1, y, res, visited);
        helper(image, x, y + 1, res, visited);
        helper(image, x + 1, y, res, visited);
        visited[x][y] = false;
    }

    public static void main(String[] args) {
        char[][] input =
                {       {'0', '0', '1', '0'},
                        {'0', '1', '1', '0'},
                        {'0', '1', '0', '0'}
                };

        MinArea minArea = new MinArea();
        System.out.println(minArea.minArea(input, 0, 2));
    }
}
