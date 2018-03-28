package com.ikuboo.guava.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class ListenableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        //将ExecutorService装饰成ListeningExecutorService
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        //通过异步的方式计算返回结果
        final ListenableFuture<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("call execute..");
                return "task success!";
            }
        });

        //有两种方法可以执行此Future并执行Future完成之后的回调函数(方法1)
        future.addListener(   //该方法会在多线程运算完的时候,指定的Runnable参数传入的对象会被指定的Executor执行
                new Runnable() {

                    public void run() {
                        try {
                            System.out.println("result: "+future.get());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }, service);
        //(方法2)
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("callback result: "+result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        },service);
        System.out.println("程序向下执行");
        for(;;){
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
    }
}
