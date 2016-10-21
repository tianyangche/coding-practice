package com.tianyangche.practice.others;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianyangche on 5/13/16.
 */
public class Sentense {
    private static final Set<Character> set = new HashSet<>();
    {
        set.add('.');
        set.add('!');
        set.add('?');
    }
    public int wordsOfLongestSentence(String input) {
        int i = 0;
        int j = 0;
        int max = 0;
        while (j < input.length()) {
            char c = input.charAt(j);
            if (set.contains(c)) {
                String sentence = input.substring(i, j).trim();
                String[] words = sentence.split("\\s+");
                max = Math.max(max, words.length);
                i = j + 1;
            }
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        String input = "";
        Sentense sentense = new Sentense();
        System.out.println(sentense.wordsOfLongestSentence(input));
    }
}
