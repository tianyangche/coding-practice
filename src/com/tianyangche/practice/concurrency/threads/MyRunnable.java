package com.tianyangche.practice.concurrency.threads;

/**
 * Created by tianyangche on 10/20/16.
 */
public class MyRunnable implements Runnable {
    private long range;
    public MyRunnable(long range) {
        this.range = range;
    }

    @Override
    public void run() {
        long sum = 0;
        for (long i = 0; i <= range; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);
    }
}
