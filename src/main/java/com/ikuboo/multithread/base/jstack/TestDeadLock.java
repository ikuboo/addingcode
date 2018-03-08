package com.ikuboo.multithread.base.jstack;

/**
 * 测试 jstack 命令
 *  jstack pid
 * Created by Administrator on 2018/3/8 0008.
 */
public class TestJstack {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){}
            }
        }, "test_jstack_commond_thread");
        t.start();

        synchronized (o){
            o.wait();
        }
    }
}
