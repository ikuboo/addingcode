package com.ikuboo.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaLimiter {
    private static RateLimiter rateLimiter = RateLimiter.create(1);
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        while (true){
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    GuavaLimiter.run();
                }
            });
        }

    }

    public static void run(){
        boolean b = rateLimiter.tryAcquire();

        if(!b){
            return;
        }

        System.out.println(String.format("%tT.run", new Date()));

    }
}
