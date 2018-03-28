package com.ikuboo.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class CallableDemo {



    public static void main(String[] args) {

        Cache<String, Long> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();

        cache.put("a", 1L);

        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Long a = cache.get("a", new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    System.out.println("load...");
                    return 1L;
                }
            });

            System.out.println("a=" + a);

            a = cache.get("a", new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    System.out.println("load...");
                    return 1L;
                }
            });
            System.out.println("a=" + a);


        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
