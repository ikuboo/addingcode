package com.ikuboo.threadlocal;

/**
 * Created by yuanchunsen on 2017/6/9.
 * ThreadLocal Test1
 */
public class Test1 {
    public static void main(String[] args) {
        final ThreadLocal<String> local = new ThreadLocal<>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String t1_s = new String("t1_s");
                local.set(t1_s);

                System.out.println("T1:" + local.get());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String t2_s = new String("t2_s");
                local.set(t2_s);
                System.out.println("T2:" + local.get());
            }
        });

        t1.start();
        t2.start();
    }
}
