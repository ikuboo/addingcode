package com.ikuboo.monitor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.AtomicLongMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 秒级监控Demo
 * @author yuanchunsen@jd.com
 * 2018/1/21.
 */
public class MonitorDemo {
    private static final Logger logger = LoggerFactory.getLogger(MonitorDemo.class);

    private static final BlockingQueue<MateData> blockingQueue = new LinkedBlockingQueue<>(10000);

    private static final ThreadPoolExecutor threadPool;

    private static final Cache<String, MateData> guavaCache;

    static {
        threadPool = new ThreadPoolExecutor(10, 10, 100, TimeUnit.SECONDS,
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

        guavaCache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .initialCapacity(1000)
                .maximumSize(1000)
                .removalListener(
                        RemovalListeners.asynchronous(new RemovalListener<String, MateData>() {
                            public void onRemoval(RemovalNotification<String, MateData> notification) {
                                System.out.println(String.format("threadName=%s,value=%s", Thread.currentThread().getName(), notification.getValue()));
                            }
                        }, threadPool)
                ).build();



    }

    /**
     * 监控方法
     */
    private static void monitor(String key,String clientId,long start){
        long end = System.currentTimeMillis();

        MateData mateData = null;

        try {
             mateData = guavaCache.get(key, new Callable<MateData>() {
                @Override
                public MateData call(){
                    return new MateData(System.currentTimeMillis() / 1000, AtomicLongMap.<String>create(), AtomicLongMap.<Long>create());
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(mateData != null){
            mateData.getClientInvokerCounter().incrementAndGet(clientId);
            mateData.getTpTotalCounter().incrementAndGet(end - start);
            mateData.getProviderInvokeTotal().incrementAndGet();
        }
    }

    public static void main(String[] args) {



    }
}
