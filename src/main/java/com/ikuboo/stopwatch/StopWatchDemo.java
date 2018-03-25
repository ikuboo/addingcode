package com.ikuboo.stopwatch;

import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/3/13.
 */
public class StopWatchDemo {
    private void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("sw_1");

        stopWatch.start("step1");
        TimeUnit.SECONDS.sleep(1);
        stopWatch.stop();

        stopWatch.start("step2");
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();


        stopWatch.start("step3");
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskInfo());
        System.out.println(stopWatch.getTaskCount());
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatchDemo stopWatchDemo = new StopWatchDemo();

        stopWatchDemo.test();
    }
}
