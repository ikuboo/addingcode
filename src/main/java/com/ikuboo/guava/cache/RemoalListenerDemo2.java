package com.ikuboo.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yuanchunsen@jd.com
 * 2018/1/21.
 */
public class RemoalListenerDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(RemoalListenerDemo2.class);

    public static void main(String[] args) {


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100000),
                new ThreadFactory() {
                    private AtomicInteger counter = new AtomicInteger(0);

                    public Thread newThread(Runnable r) {
                        return new Thread(r, "guava-cache-threads-" + counter.getAndIncrement());
                    }
                },

                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        logger.warn("Task {} rejected from {}", r.toString(), executor.toString());
                    }
                }
        );


        Cache<String, AtomicLong> guavaCache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .initialCapacity(1000)
                .maximumSize(1000)
                .removalListener(
                        RemovalListeners.asynchronous(new RemovalListener<String, AtomicLong>() {
                            public void onRemoval(RemovalNotification<String, AtomicLong> notification) {
                                logger.info(String.format("threadName=%s,value=%s", Thread.currentThread().getName(), notification.getValue().get()));
                            }
                        }, threadPool)
                )
                .build();

        while (true) {
            try {
                AtomicLong key = guavaCache.get("key", new Callable<AtomicLong>() {
                    @Override
                    public AtomicLong call() throws Exception {
                        return new AtomicLong(0);
                    }
                });

                key.incrementAndGet();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
