package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/25/16.
 */
public class IntegerToEnglishWords {
    private static final String[] GROUPS = {"Billion", "Million", "Thousand"};
    private static final int[] UNITES = {1000000000, 1000000, 1000};
    private static final String[] DIGITS = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {

        String[] strs = {"", "", ""};
        boolean hasBillion = false;
        boolean hasMillion = false;
        boolean hasThousand = false;

        int i = 0;
        while (i < 3) {
            if (num / UNITES[i] != 0) {
                strs[i] = convert(num / UNITES[i]);
                num %= UNITES[i];
            } else {

            }
            i++;
        }

        return getResult(strs, num);
    }

    private String convert(int num) {
        StringBuilder  sb = new StringBuilder();
        if (num / 100 != 0) {
            sb.append(DIGITS[num / 100]).append(" Hundred");
            num %= 100;
        }

        if (num == 0) {
            if (sb.length() > 0) {
                 return sb.toString();
            }
        }
        if (num < 20) {
            sb.append(DIGITS[num]);
        } else {
            if (num % 10 == 0) {
                sb.append(TENS[num / 10 - 2]);
            } else {
                sb.append(TENS[num / 10 - 2]);
                num %= 10;
                sb.append(" ").append(DIGITS[num]);
            }
        }

        return sb.toString();
    }

    private String getResult(String[] strs, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (!strs[i].isEmpty()) {
                sb.append(strs[i]).append(" ").append(GROUPS[i]).append(" ");
            }
        }
        if (sb.length() == 0) {
            return convert(num);
        } else {
            if (num == 0) {
                String res = sb.toString();
                return res.substring(0, res.length() - 1);
            } else {
                sb.append(convert(num));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 10000001;
        IntegerToEnglishWords converter = new IntegerToEnglishWords();
        System.out.println(converter.numberToWords(num));
    }

}
