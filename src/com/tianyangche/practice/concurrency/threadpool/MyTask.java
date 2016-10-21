package com.tianyangche.practice.concurrency.threadpool;

import com.oracle.tools.packager.Log;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by tianyangche on 10/20/16.
 */

public class MyTask implements Runnable {

    private int taskNum;

    public MyTask(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void run() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Executing task-" + taskNum + " at " + dateFormat.format(new Date()));
        Log.info("Yes");
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task-" + taskNum + " is complete at " + dateFormat.format(new Date()));
    }
}
