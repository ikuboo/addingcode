package com.ikuboo.monitor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.AtomicLongMap;
import com.sun.javafx.binding.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
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
                                MateData value = notification.getValue();

                                Map<String, Long> groupMap = value.getClientInvokerCounter().asMap();
                                Map<Long, Long> tpMap = value.getTpTotalCounter().asMap();


                                Date now = new Date();
                                Date current = new Date(value.getCurrentTime() * 1000);

                                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");

                                System.out.println("-------------------------");
                                System.out.println("当前时间:" + sdf.format(now));
                                System.out.println("统计时间:" + sdf.format(current));

                                for(String key : groupMap.keySet()){
                                    System.out.println(key + ":" + groupMap.get(key));
                                }

                                System.out.println("all" + ":" + value.getProviderInvokeTotal().get());

                                for(Long key : tpMap.keySet()){
                                    System.out.println(key + ":" + tpMap.get(key));
                                }

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

    public static void main(String[] args) throws InterruptedException {

        while (true){
            long begin = System.currentTimeMillis();
            Thread.sleep(new Random().nextInt(10));
            if(new Random().nextInt(100) % 2 == 0){
                monitor("com.ikuboo.monitor.MonitorDemo", "PC", begin);
            }else {
                monitor("com.ikuboo.monitor.MonitorDemo", "M", begin);
            }

        }
    }
}
