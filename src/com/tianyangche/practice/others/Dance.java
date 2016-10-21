package com.tianyangche.practice.others;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dance {
    public static void dance(int D, int K, int N, int caseNumber) {
        int[] dancers = new int[D];
        for (int i = 0; i < D; i++) {
            dancers[i] = i + 1;
        }

        for (int i = 0; i < D; i++) {
            if ((i + 1) % 2 == 1) {
                dancers[i] = (i + N + D) % D;
            } else {
                dancers[i] = ((i - N) % D + D) % D;
            }
        }

        int pos = dancers[K - 1];

        int left = 0;
        int right = 0;

        for (int i = 0; i < D; i++) {
            if (dancers[i] == (dancers[K - 1] - 1 + D) % D) {
                left = i + 1;
            }
            if (dancers[i] == (dancers[K - 1] + 1) % D) {
                right = i + 1;
            }
        }

        //        System.out.println("Case #" + caseNumber + ": " + dancers[(pos + 1 + D) % D] + " " + dancers[(pos - 1 + D) % D]);
        System.out.println("Case #" + caseNumber + ": " + right + " " + left);
    }
    private static void swap(int[] dancers, int i, int j) {
        int temp = dancers[i];
        dancers[i] = dancers[j];
        dancers[j] = temp;
    }


    public static void main(String[] args) {
//        int D = 0;
//        int K = 0;
//        int N = 0;
//        String fileName = "/Users/tianyangche/Desktop/B-large.in";
//        String line = null;
//
//        try {
//            FileReader fileReader = new FileReader(fileName);
//            BufferedReader bufferedReader =
//                    new BufferedReader(fileReader);
//            int caseNumber = 0;
//            while ((line = bufferedReader.readLine()) != null) {
//                int lineNumber = Integer.valueOf(line);
//
//                for (int i = 0; i < lineNumber; i++) {
//                    caseNumber++;
//                    line = bufferedReader.readLine();
//                    String[] elements = line.split("\\s+");
//
//                    D = Integer.valueOf(elements[0]);
//                    K = Integer.valueOf(elements[1]);
//                    N = Integer.valueOf(elements[2]);
//
//                    dance(D, K, N, caseNumber);
//                }
//            }
//            bufferedReader.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println(
//                    "Unable to open file '" +
//                            fileName + "'");
//        } catch (IOException ex) {
//            System.out.println(
//                    "Error reading file '"
//                            + fileName + "'");
//        }

//        Map<Character, Integer> map = new HashMap<Character>
    }
}
