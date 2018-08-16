package com.ikuboo.guava.future;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * callback 形式
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class ListenableFutureDemo2 {
    public static void main(String[] args) throws IOException {
        //将ExecutorService装饰成ListeningExecutorService
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        //通过异步的方式计算返回结果
        final ListenableFuture<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("ListenableFutureDemo1.call" + getThreadName());
                return "task success!";
            }
        });


        //callback方式
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("callback result: "+result+ getThreadName());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage()  + getThreadName());
            }
        },service);

        System.in.read();
    }


    public static String getThreadName(){
        return "-threadName:" + Thread.currentThread().getName();
    }
}
