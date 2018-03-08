package com.ikuboo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/2/24.
 */
public class Test2 {


    public static void main(String[] args) throws InterruptedException {
        final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>(){
            @Override
            protected Long initialValue()
            {
                return 0L;
            }
        };


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyTask(threadLocal, i));
            TimeUnit.SECONDS.sleep(3);
        }




    }

}

class MyTask implements Runnable{
    private ThreadLocal<Long> threadLocal;
    private int loop;
    public MyTask(ThreadLocal<Long> threadLocal, int loop){
        this.threadLocal = threadLocal;
        this.loop = loop;
    }
    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        if(loop == 0){
            threadLocal.set(10L);
        }

        System.out.println("Thread.name" + name + ";value=" + threadLocal.get());


    }
}
