package com.tianyangche.practice.concurrency.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianyangche on 10/20/16.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(1000));
        for (int i = 0; i < 200; i++) {
            MyTask myTask = new MyTask(i);
            try {
                executor.execute(myTask);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Number of threads = " + executor.getPoolSize() + ". Number of tasks waiting in the queue = " + executor.getQueue().size()
                        + ". Number of completed task = " + executor.getCompletedTaskCount());
            }
        }
        System.out.println("Reach the end...");
        executor.shutdown();
    }
}
