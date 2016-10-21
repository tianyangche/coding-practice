package com.tianyangche.practice.others;

import java.util.Arrays;

/**
 * Created by tianyangche on 2/24/16.
 */
public class Practice {
    public static int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] < target) {
                start = middle;
            } else {
                end = middle;
            }
        }

        if (array[start] == target) {
            return start;
        }
        if (array[end] == target) {
            return target;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 5, 6};
//        System.out.println(binarySearch(array, 0));
        boolean[][] res = makePalindromeMatrix("aba");
        printMatrix(res);
    }


    private static boolean[][] makePalindromeMatrix(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (boolean[] row : isPalindrome) {
            Arrays.fill(row, true);
        }
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; i + j < n; j++) {
                isPalindrome[j][i + j] = i == 1 ? s.charAt(j) == s.charAt(i + j) : (s.charAt(j) == s.charAt(i + j) && isPalindrome[j + 1][i + j - 1]);
            }
        }

        return isPalindrome;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
    }
}
