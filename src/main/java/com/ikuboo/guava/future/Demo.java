package com.ikuboo.guava.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final Object lock = new Object();
        Integer result = 0;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return 100;
            }
        });

        try {
            result = submit.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("超时");
        }

        System.out.println("result=" + result);

        executorService.shutdown();
    }
}
