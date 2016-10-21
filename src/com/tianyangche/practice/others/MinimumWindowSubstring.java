package com.tianyangche.practice.others;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianyangche on 3/12/16.
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (t == null || t.length() == 0) {
            return s;
        }

        Map<Character, Integer> target = new HashMap<Character, Integer>();
        Map<Character, Integer> curr = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (target.containsKey(c)) {
                target.put(c, target.get(c) + 1);
            } else {
                target.put(c, 1);
            }
            curr.put(c, 0);
        }

        int i = 0;
        int j = 0;
        int count = 0;
        int pos = 0;
        int res = Integer.MAX_VALUE;

        for (; i < s.length(); i++) {
            while (j < s.length() && count != target.size()) {
                char c = s.charAt(j);
                j++;
                if (target.containsKey(c)) {
                    curr.put(c, curr.get(c) + 1);
                    //System.out.println(curr.get(c) + "\t" + target.get(c));
                    if (curr.get(c).equals(target.get(c))) {
                        System.out.println("++ " + i);
                        count++;
                    }
                }
            }
            if (count == target.size() && j - i< res) {
                res = j - i;
                pos = i;
            }

            char d = s.charAt(i);
            if (curr.containsKey(d)) {
                curr.put(d, curr.get(d) - 1);
                if (curr.get(d) < target.get(d)) {
                    count--;
                }
            }
        }
//        System.out.println(count + "\t" + target.size());
        if (res == Integer.MAX_VALUE) {
            return "abc";
        }

        return s.substring(pos, pos + res);

    }


    public static void main(String[] args) {

        String s = "";
        String t = "";



        String fileName = "/Users/tianyangche/Desktop/test.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            s = bufferedReader.readLine();
            t = bufferedReader.readLine();
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

        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();

        System.out.println(minimumWindowSubstring.minWindow(s, t));



    }


}
