package com.tianyangche.practice.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by tianyangche on 3/7/16.
 */
public class TrappingRainWater {

    class Cell {
        int i;
        int j;
        int h;
        public Cell(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }
    }

    class CellComparator implements Comparator<Cell> {
        public int compare(Cell c1, Cell c2) {
            if (c1.h > c2.h) {
                return 1;
            }
            if (c1.h < c2.h) {
                return -1;
            }
            return 0;
        }
    }

    public int trappingRainWaterII(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 1. convert to customized class
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell(i, j, matrix[i][j]);
            }
        }
        boolean[][] visited = new boolean[m][n];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }
        // 2. Put outlier into Heap

        PriorityQueue<Cell> heap = new PriorityQueue<>(m * n, new CellComparator());
        for (int i = 0; i < m; i++) {
            heap.offer(cells[i][0]);
            visited[i][0] = true;
            heap.offer(cells[i][n - 1]);
            visited[i][n - 1] = true;
        }

        for (int i = 0; i < n; i++) {

            heap.offer(cells[0][i]);
            visited[0][i] = true;
            heap.offer(cells[m - 1][i]);
            visited[m - 1][i] = true;
        }


        // 3. Go through every element in heap, for each element, find its adjacent cells. If some neighbor hasn't been visited, update its height and put it into heap

        int ans = 0;
        while (!heap.isEmpty()) {
            Cell curr = heap.poll();
            if (curr.i - 1 >= 0 && !visited[curr.i - 1][curr.j]) {
                visited[curr.i - 1][curr.j] = true;
                ans += Math.max(0, curr.h - matrix[curr.i - 1][curr.j]);
                cells[curr.i - 1][curr.j].h = Math.max(curr.h, matrix[curr.i - 1][curr.j]);
                heap.offer(cells[curr.i - 1][curr.j]);
            }

            if (curr.i + 1 < m && !visited[curr.i + 1][curr.j]) {
                visited[curr.i + 1][curr.j] = true;
                ans += Math.max(0, curr.h - matrix[curr.i + 1][curr.j]);
                cells[curr.i + 1][curr.j].h = Math.max(curr.h, matrix[curr.i + 1][curr.j]);
                heap.offer(cells[curr.i + 1][curr.j]);
            }

            if (curr.j - 1 >= 0 && !visited[curr.i][curr.j - 1]) {
                visited[curr.i][curr.j - 1] = true;
                ans += Math.max(0, curr.h - matrix[curr.i][curr.j - 1]);
                cells[curr.i][curr.j - 1].h = Math.max(curr.h, matrix[curr.i][curr.j - 1]);
                heap.offer(cells[curr.i][curr.j - 1]);
            }


            if (curr.j + 1 < n && !visited[curr.i][curr.j + 1]) {
                visited[curr.i][curr.j + 1] = true;
                ans += Math.max(0, curr.h - matrix[curr.i][curr.j + 1]);
                cells[curr.i][curr.j + 1].h = Math.max(curr.h, matrix[curr.i][curr.j + 1]);
                heap.offer(cells[curr.i][curr.j + 1]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {12, 13, 0, 12},
                {13, 4, 13, 12},
                {13, 8, 10, 12},
                {12, 13, 12, 12},
                {13, 13, 13, 13}
        };

        TrappingRainWater solution = new TrappingRainWater();
        System.out.println(solution.trappingRainWaterII(matrix));
    }

}
