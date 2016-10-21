package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/1/16.
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        String res = "";
        n--;
        while (n / 26 != 0) {
            int i = n % 26;
            res = (char)(i + 'A') + res;
            n /= 26;
            n--;
        }
        res = (char)(n + 'A') + res;
        return res;
    }

    public static void main(String[] args) {
        ExcelSheetColumnTitle title = new ExcelSheetColumnTitle();
        for (int i = 1; i <= 100; i++) {
            System.out.println(title.convertToTitle(i));
        }
    }
}
