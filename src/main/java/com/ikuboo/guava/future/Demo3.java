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
public class Demo3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Object lock = new Object();

        ExecutorService threalPool = Executors.newSingleThreadExecutor();

        Future<String> submit = threalPool.submit(new MyCall3(lock));

        String result;
        synchronized (lock) {
            System.out.println("begin wait..");
            lock.wait(1000);
            if(submit.isDone()){
                result = submit.get();
            }else {
                result = null;
            }
        }

        System.out.println("wait end..");

        System.out.println("result=" + result);
        threalPool.shutdown();

    }
}

class MyCall3 implements Callable<String>{
    private Object lock;
    public MyCall3(Object lock){
        this.lock = lock;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        synchronized (lock) {
            lock.notify();
        }
        return "MyCall3 success!";
    }
}

