package com.tianyangche.practice.interview.dropbox.phone.phone;

import java.util.Date;

/**
 * Created by tianyangche on 6/12/16.
 */
public class WebServerRequestCounter {
    private static final int N = 5;
    private int[] elements;
    private int sum;
    private int lastTime;
    private int lastPosition;

    public WebServerRequestCounter() {
        elements = new int[N];
        sum = 0;
        Date date = new Date();
        lastTime = (int)(date.getTime() / 1000);
        lastPosition = 0;
    }

    private void update() {
        Date date = new Date();
        int currTime = (int)(date.getTime() / 1000);
        int gap = Math.min(currTime - lastTime, N);
        for (int k = 0; k < gap; k++) {
            lastPosition = (lastPosition + 1) % N;
            sum -= elements[lastPosition];
            elements[lastPosition] = 0;
        }
        lastTime = currTime;
    }

    public void hit() {
        update();
        elements[lastPosition]++;
        sum++;
    }

    public int getHit() {
        update();
        return sum;
    }
    public static void main(String[] args) throws InterruptedException {
        WebServerRequestCounter counter = new WebServerRequestCounter();
        Date date = new Date();
        for (int i = 0; i < 100; i++) {
            counter.hit();
        }
        Thread.sleep(1000 * 4);
        System.out.println(counter.getHit());
    }
}
