package com.ikuboo.threadlocal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Test4 {
    private Integer appleCount = 9;

    public synchronized boolean takeApple(Integer count) {
        if (appleCount < count) {
            return false;
        }
        appleCount -= count;
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        final Test4 t = new Test4();
        int i = 0;
        while (true) {
            final int count = i++ % 2 == 0 ? 2 : 3;
            FutureTask<Boolean> myTask = new FutureTask(new MyCall(t, count));

            Future<Boolean> submit = threadPool.submit(new MyCall(t, count));

            if (!submit.get()) {
                break;
            }
            System.out.println("获取苹果:" + count);
        }
        threadPool.shutdown();

    }

}

class MyCall implements Callable<Boolean> {
    private Test4 t;
    private Integer count;

    public MyCall(Test4 test, Integer count) {
        this.t = test;
        this.count = count;
    }

    public Boolean call() {
        return t.takeApple(count);
    }
}
