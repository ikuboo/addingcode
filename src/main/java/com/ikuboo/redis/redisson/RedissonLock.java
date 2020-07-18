package com.ikuboo.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;

/**
 * redission 分布式重入锁
 */
public class RedissonLock {

    public synchronized static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        final CountDownLatch latch = new CountDownLatch(2);
        final RLock lock = redisson.getLock("lock_a");

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("线程1获取锁");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println("线程1释放锁");
                latch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("线程2获取锁");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println("线程2释放锁");
                latch.countDown();
            }
        }).start();

        latch.wait();
        System.out.println("程序结束");
    }
}
