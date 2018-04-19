package com.ikuboo.guava.collect;

import com.google.common.util.concurrent.AtomicLongMap;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuanchunsen@jd.com
 * 2018/4/19.
 */
public class AtomicLongMapDemo {

    //线程安全 底层用 ConcurrentHashMap，AtomicLong
    private static final AtomicLongMap<String> atomicLongMapString = AtomicLongMap.create();
    private static final AtomicLongMap<Long> atomicLongMapLong = AtomicLongMap.create();

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(100000);

        for(int i = 0; i < 100; i++){

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        atomicLongMapString.incrementAndGet("key1");
                        atomicLongMapString.incrementAndGet("key2");

                        atomicLongMapLong.incrementAndGet(1001L);
                        atomicLongMapLong.incrementAndGet(1002L);

                        countDownLatch.countDown();
                    }

                }
            });
            t1.start();
        }


        countDownLatch.await();

        long key1 = atomicLongMapString.get("key1");
        long key2 = atomicLongMapString.get("key2");
        long long1 = atomicLongMapLong.get(1001L);
        long long2= atomicLongMapLong.get(1001L);

        System.out.println("key1:" + key1);
        System.out.println("key2:" + key2);
        System.out.println("long1:" + long1);
        System.out.println("long2:" + long2);

    }

}
