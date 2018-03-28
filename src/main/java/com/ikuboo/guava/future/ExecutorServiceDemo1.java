package com.ikuboo.guava.future;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class ExecutorServiceDemo1 {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(new MyRun(), 1, 5, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(30);

        executorService.shutdown();
        System.out.println("shutdow");
        System.out.println(executorService.isShutdown());



    }
}
 class MyRun implements Runnable{


     @Override
     public void run() {
         System.out.print(".");
     }
 }
