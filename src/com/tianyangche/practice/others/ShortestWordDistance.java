package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/1/16.
 */
public class ShortestWordDistance {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int a = 0 - words.length * 2;
        int b = 0 - words.length * 2;

        int ans = Integer.MAX_VALUE;
        if (word1.equals(word2)) {
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    ans = Math.min(ans, i - b);
                    a = i;
                } else if (word2.equals(words[i])) {
                    ans = Math.min(ans, i - a);
                    b = i;
                }
            }
        } else {
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i]) || word2.equals(words[i])) {
                    ans = Math.min(ans, i - a);
                    a = i;
                }
            }
        }


        return ans;
    }


    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistance swd = new ShortestWordDistance();
    }

}
