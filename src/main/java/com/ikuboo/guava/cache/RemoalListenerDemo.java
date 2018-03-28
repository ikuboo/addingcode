package com.ikuboo.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class RemoalListenerDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final LoadingCache<String, Long> cache= CacheBuilder.newBuilder()
                .maximumSize(100) //最大缓存数目
                .expireAfterAccess(5, TimeUnit.SECONDS) //缓存5秒后过期
                .removalListener(new RemovalListener<String, Long>() {//过期监听器
                    @Override
                    public void onRemoval(RemovalNotification<String, Long> removalNotification) {
                        System.out.println("过期:" + removalNotification.getKey() + ":" + removalNotification.getValue());
                    }
                })
                .build(new CacheLoader<String, Long>() {
                    @Override
                    public Long load(String key) throws Exception {
                        System.out.println("loading to cache....;key=" + key);
                        return System.currentTimeMillis();
                    }
                });



        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        //只有在get/cleanup 的时候 才会处罚过期监听器
                        TimeUnit.SECONDS.sleep(1);
                        System.out.print(".");
                        cache.cleanUp();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        cache.put("a",1L);
        TimeUnit.SECONDS.sleep(5);
        cache.put("b",2L);
        TimeUnit.SECONDS.sleep(5);
        cache.put("c",3L);
    }
}
