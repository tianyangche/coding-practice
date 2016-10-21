package com.tianyangche.practice.others;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class RegularPrice {

    public static void sortPrice(int[] input, int caseNumber) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : input) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] small = new int[input.length / 2 + 1];
        int[] large = new int[input.length / 2 + 1];
        int count = 0;
        int k = 0;
        for (int salePrice : input) {
            if (count == input.length / 2) {
                break;
            }
            if (map.containsKey(salePrice)) {
                int regularPrice = salePrice / 3 * 4;
                if (!map.containsKey(regularPrice)) {
                    continue;
                }
                if (map.get(salePrice) == 1) {
                    map.remove(salePrice);
                } else {
                    map.put(salePrice, map.get(salePrice) - 1);
                }

                if (map.get(regularPrice) == 1) {
                    map.remove(regularPrice);
                } else {
                    map.put(regularPrice, map.get(regularPrice) - 1);
                }
                small[count] = salePrice;
                large[count] = regularPrice;
                count++;
            }
        }
        System.out.print("Case #" + caseNumber + ": ");
        for (int j = 0; j < input.length / 2; j++) {
            System.out.print(small[j] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        String fileName = "/Users/tianyangche/Desktop/2.in";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int caseNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                int lineNumber = Integer.valueOf(line);

                for (int i = 0; i < lineNumber; i++) {
                    caseNumber++;
                    line = bufferedReader.readLine();
                    int itemSize = Integer.parseInt(line);
                    int[] input = new int[itemSize * 2];
                    line = bufferedReader.readLine();
                    String[] elements = line.split("\\s+");
                    for (int j = 0; j < itemSize * 2; j++) {
                        input[j] = Integer.valueOf(elements[j]);
                    }
                    sortPrice(input, caseNumber);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }
}
