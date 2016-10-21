package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/10/16.
 */
public class ReverseWords {
    public void reverseWords(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        int i = s.length - 1;
        int j = s.length - 1;

        while (i >= 0) {
            if (i >= 0 && s[i] != ' ') {
                i--;

            }
            System.out.println(i + "\t" + j);
            swapGroup(s, i + 1, j);
            // System.out.println("1");
            if (i < 0) {
                break;
            }
            i = i - 1;
            j = i;
        }
        // System.out.println("2");
        swapGroup(s, 0, s.length - 1);
    }

    private void swapGroup(char[] s, int start, int end) {
        System.out.println("yes");
        while (start < end) {
            swap(s, start, end);
            start++;
            end--;
        }
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        String input = "hi!";

        ReverseWords reverseWords = new ReverseWords();
        char[] array = input.toCharArray();
        reverseWords.reverseWords(array);
        System.out.println(array);
    }
}
