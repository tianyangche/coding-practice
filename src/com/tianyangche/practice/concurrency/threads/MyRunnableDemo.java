package com.tianyangche.practice.concurrency.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 10/20/16.
 */
public class MyRunnableDemo {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Runnable task = new MyRunnable(100000 * 100000 + i * i * 100);
            Thread thread = new Thread(task);
            thread.setName("MyThread-" + i);
            thread.start();
            threads.add(thread);
        }
        int running = 0;
        do {
            running = 0;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    running++;
                }
            }
            System.out.println(running + " threads are still running.");

        } while (running > 0);
        System.out.println("All threads are finished.");
    }
}
