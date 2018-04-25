package com.ikuboo.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class RemoalListenerDemo3 {
    private static  final Logger logger = LoggerFactory.getLogger("DemoLogger");
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final Cache<String, AtomicLong> cache= CacheBuilder.newBuilder()
                .maximumSize(100) //最大缓存数目
                .expireAfterAccess(5, TimeUnit.SECONDS) //缓存5秒后过期
                .removalListener(new RemovalListener<String, AtomicLong>() {//过期监听器
                    @Override
                    public void onRemoval(RemovalNotification<String, AtomicLong> removalNotification) {
                        logger.error("过期:" + removalNotification.getKey() + ":" + removalNotification.getValue().get());
                    }
                })
                .build();



        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        //只有在get/cleanup 的时候 才会处罚过期监听器
                        TimeUnit.SECONDS.sleep(1);
                        cache.cleanUp();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        logger.error("put key1");
        AtomicLong key1 = cache.get("key1", new Callable<AtomicLong>() {
            @Override
            public AtomicLong call() {
                return new AtomicLong(0);
            }
        });
        key1.incrementAndGet();

        Thread.sleep(3000);
        logger.error("put key2");
        AtomicLong key2 = cache.get("key2", new Callable<AtomicLong>() {
            @Override
            public AtomicLong call() {
                return new AtomicLong(0);
            }
        });
        key2.getAndAdd(10);


        Thread.sleep(100000);
    }
}
