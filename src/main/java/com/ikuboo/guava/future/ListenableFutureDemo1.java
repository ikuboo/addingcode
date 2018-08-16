package com.ikuboo.guava.future;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;
import com.sun.tools.classfile.Opcode;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 监听器形式
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class ListenableFutureDemo1 {
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
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
        //监听器的形式，future done的时候，会执行run方法，此时可以调用future.get方法获取返回值。
        future.addListener(
                new Runnable() {
                    public void run() {
                        try {
                            System.out.println("ListenableFutureDemo1.run");
                            System.out.println("result: "+future.get()+ getThreadName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }, service);

        System.out.println("main go run" + getThreadName());
        System.in.read();
    }


    public static String getThreadName(){
        return "-threadName:" + Thread.currentThread().getName();
    }
}
