package com.ikuboo.multithread.atomic;

import java.util.concurrent.CountDownLatch;

/**
 * 测试long和double类型 赋值的原子性
 */
public class LongDoubleAtomicTest {
    public static void main(String[] args) throws InterruptedException {
        //final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(new RunnableImpl(0));
        Thread t2 = new Thread(new RunnableImpl(1));

        t1.start();
        t2.start();

        //Thread.sleep(1000);

        while (RunnableImpl.l == 0 || RunnableImpl.l == 1){
            System.out.print(".");
        }

        System.out.println("end");
        t1.interrupt();
        t2.interrupt();
    }
}

class RunnableImpl implements Runnable{

    private final int param;

    public RunnableImpl(int param) {
        this.param = param;
    }

    public static  int l=0;

    @Override
    public void run() {
        while (!Thread.interrupted()){
            l = param;
        }
    }
}
