package com.tianyangche.practice.interview.facebook;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tianyangche on 6/6/16.
 */
public class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int distance() throws NoSuchAlgorithmException {
        MessageDigest file = MessageDigest.getInstance("MD5");

        return x * x + y * y;
    }
}