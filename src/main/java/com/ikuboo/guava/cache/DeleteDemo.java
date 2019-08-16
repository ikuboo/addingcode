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
public class DeleteDemo {



    public static void main(String[] args) {

        Cache<Verson, Boolean> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();

        cache.put(new Verson(), false);

        //cache.

    }
}

class Verson{
     String key;
     String version;
    String ctype;
}
