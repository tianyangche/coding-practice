package com.tianyangche.design;



import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by tianyangche on 7/31/16.
 */
public class TinyURL {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final String PREFIX = "http://tiny.url/";
    private static final char[] DICTIONARY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private Map<String, String> shortToLong = new HashMap<>();
    private Map<String, String> longToShort = new HashMap<>();

    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        if (longToShort.containsKey(url)) {
            return PREFIX + longToShort.get(url);
        }
        Random random = new Random();
        int value = random.nextInt(MAX_VALUE);
        while (longToShort.containsKey(value)) {
            value = random.nextInt(MAX_VALUE);
        }
        String shortURL = convertToShort(value);
        shortToLong.put(shortURL, url);
        longToShort.put(url, shortURL);
        // System.out.println(shortURL);
        return PREFIX + shortURL;
    }


    private String convertToShort(int value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(DICTIONARY[value % 62]);
            value = value / 62;
        }
        return builder.toString();
    }
    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        // Write your code here

        return shortToLong.get(url.substring(PREFIX.length()));
    }


}
