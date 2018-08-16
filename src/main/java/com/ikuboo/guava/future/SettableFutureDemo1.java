package com.ikuboo.guava.future;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class SettableFutureDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //将ExecutorService装饰成ListeningExecutorService
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        //通过异步的方式计算返回结果
        final ListenableFuture<String> future1 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("call1 execute.."+ getThreadName());
                return "task1 success!";
            }
        });

        final ListenableFuture<String> future2 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("call2 execute.."+ getThreadName());
                int a = 0;
                int b = 1/a;
                return "task2 success!";
            }
        });

        //allAsList有未成功的会抛出异常
        //ListenableFuture<List<String>> listListenableFuture = Futures.allAsList(future1, future2);

        //successfulAsList不会抛出异常，结果为null
        ListenableFuture<List<String>> listListenableFuture = Futures.successfulAsList(future1, future2);
        List<String> results = listListenableFuture.get();
        for(String result : results){
            System.out.println(result);
        }

    }


    public static String getThreadName(){
        return "-threadName:" + Thread.currentThread().getName();
    }
}
