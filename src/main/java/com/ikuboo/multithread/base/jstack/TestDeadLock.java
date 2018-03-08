package com.ikuboo.multithread.base.jstack;

/**
 * 用 jstack 命令 分析死锁
 *  jstack pid
 * Created by Administrator on 2018/3/8 0008.
 */
public class TestDeadLock {
    public static void main(String[] args) throws InterruptedException {
        //deadLock方法会出发死锁
        //t1 thead 持有A锁等待B锁，t2 线程持有B锁等A锁，故会造成死锁
        TestDeadLock.deadLock();

    }

    private static String A = "A";
    private static String B = "B";

    private static void deadLock(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.sleep(2000);
                        synchronized (B){
                            System.out.println("t1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                   try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("t2");
                    }
                }
            }
        });


        t1.start();
        t2.start();
    }
}
