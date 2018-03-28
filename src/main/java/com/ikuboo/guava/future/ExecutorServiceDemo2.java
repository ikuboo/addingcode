package com.ikuboo.guava.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class ExecutorServiceDemo2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final Object lock = new Object();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(new MyCall());

        System.out.println("程序继续执行");
        while (true){
            boolean isDone = submit.isDone();
            System.out.println("isDone:" + isDone);
            if(isDone){
                break;
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(submit.get());


    }
}
class MyCall implements Callable<String>{

    @Override
    public String call() throws Exception {

        TimeUnit.SECONDS.sleep(5);
        return "MyRun";
    }
}
