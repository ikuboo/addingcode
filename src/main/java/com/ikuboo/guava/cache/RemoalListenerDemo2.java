package com.ikuboo.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class RemoalListenerDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(RemoalListenerDemo2.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,10,100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100000),
                new ThreadFactory() {
                    private AtomicInteger counter = new AtomicInteger(0);
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "guava-cache-thread-" + counter.getAndIncrement());
                    }
                },

                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        logger.warn("Task {} rejected from {}",r.toString(), executor.toString());
                    }
                }
        );


        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .initialCapacity(1000)
                .maximumSize(1000)
                .removalListener(
                        RemovalListeners.asynchronous(new RemovalListener<String, String>() {
                            public void onRemoval(RemovalNotification<String, String> notification) {
                                   logger.info(notification.getValue());
                            }
                        }, threadPool)
                )
                .build();


        Long value = 0L;
        for (;;){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SSS");
            String nowStr = sdf.format(new Date());
            cache.put("key:" + nowStr, nowStr);
            TimeUnit.SECONDS.sleep(3);
        }


    }
}
