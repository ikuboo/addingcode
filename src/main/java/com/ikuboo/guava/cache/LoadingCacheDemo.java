package com.ikuboo.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class LoadingCacheDemo {
    public static void main(String[] args) {
        LoadingCache<String, Long> cache= CacheBuilder.newBuilder()
                .maximumSize(100) //最大缓存数目
                .expireAfterAccess(1, TimeUnit.SECONDS) //缓存1秒后过期
                .removalListener(new RemovalListener<String, Long>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Long> removalNotification) {
                        System.out.println("过期:" + removalNotification.getKey() + ":" + removalNotification.getValue());
                    }
                })
                .build(new CacheLoader<String, Long>() {
                    @Override
                    public Long load(String key) throws Exception {
                        System.out.println("loading to cache....;key=" + key);
                        return 0L;
                    }
                });

        cache.put("a",1L);
        cache.put("b",2L);
        cache.put("c",3L);
        cache.put("d",4L);
        cache.put("e",5L);

        try {
            System.out.println(cache.get("a"));
            TimeUnit.SECONDS.sleep(2);
            //System.out.println(cache.getUnchecked("b"));
            System.out.println(cache.get("a")); //输出s
            System.out.println(cache.get("b")); //输出s
            System.out.println(cache.get("c")); //输出s
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
