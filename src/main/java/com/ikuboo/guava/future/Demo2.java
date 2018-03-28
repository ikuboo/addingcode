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
public class Demo2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(new MyCall2());

        for (int i = 0; i < 10; i++) {
            System.out.println("loop:" + i + ";isCancelled:" + submit.isCancelled());
            if(3 == i){
                submit.cancel(true);
            }
            TimeUnit.SECONDS.sleep(1);
        }



        executorService.shutdown();
    }
}
class MyCall2 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("myCall begin");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("myCall end");
        return "myCall success!";
    }
}
