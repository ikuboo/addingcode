package com.ikuboo.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 * @author yuanchunsen@jd.com
 *         2018/3/21.
 */
public class ReadWritelock {
    public static void main(String[] args) throws InterruptedException {
        final Resource r = new Resource();
        for(int i=0; i < 2; i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + ";flag=" + r.getFlag());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, "thread_get_" + i);
            t.start();
        }
        Thread.sleep(5000);

        for(int i=0; i < 100; i++){
            Thread t = new Thread(new Run1(r), "thread_set_" + i);
            t.start();
        }
        Thread.sleep(500000);
    }
}

class Run1 implements Runnable{

    private Resource r;
    public Run1(Resource r){
        this.r = r;

    }

    @Override
    public void run() {
        for(int i = 0; i < 1000;i++){
            r.setFlag();
        }
    }
}

class Resource {
    private int flag = 0;

    private final ReentrantReadWriteLock reentrantReadWriteLock;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    public Resource(){
        reentrantReadWriteLock = new ReentrantReadWriteLock();
        readLock = reentrantReadWriteLock.readLock();
        writeLock = reentrantReadWriteLock.writeLock();
    }

    public int getFlag() {
        readLock.lock();
        try{
            return flag;
        }finally {
            readLock.unlock();
        }

    }

    public void setFlag() {
        writeLock.lock();
        try{
        this.flag = this.flag + 1;
        }finally {
            writeLock.unlock();
        }
    }
}
