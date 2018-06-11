package com.ikuboo.guava.ratelimiter;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

public class StopwatchDemo {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        System.out.println(stopwatch.elapsed(MICROSECONDS));
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(stopwatch.elapsed(MICROSECONDS));
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(stopwatch.elapsed(MICROSECONDS));
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(stopwatch.elapsed(MICROSECONDS));

    }
}
