package com.ikuboo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.*;

/**
 * Redis 管道测试
 */
public class RedisPipelinedTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.3.122", 6379);
        RedisPipelinedTest t  = new RedisPipelinedTest();

        //t.setTest(jedis);
        //t.exceptionTest(jedis);
        //t.getTest(jedis);
        t.mgetTest(jedis);
    }

    public void setTest(Jedis jedis){
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String key = "test1_" + i;
            jedis.set(key, "value1_" + i);
            jedis.expire(key,3600);
        }
        System.out.println("sync:" + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            String key = "test2_" + i;
            pipelined.set(key, "value2_" + i);
            pipelined.expire(key,3600);
        }
        pipelined.sync();
        System.out.println("async:" + (System.currentTimeMillis() - begin));
    }

    public void exceptionTest(Jedis jedis){
        Pipeline pipelined = jedis.pipelined();

        try{
            for (int i = 0; i < 10; i++) {
                String key = "exception_" + i;
                pipelined.set(key, "value2_" + i);
                pipelined.expire(key,3600);
                if(i == 5){
                    throw new RuntimeException("异常了");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        pipelined.sync();

        Set<String> keys = jedis.keys("exception_*");

        for (String key : keys) {
            System.out.println(key);
        }
    }

    public void getTest(Jedis jedis){
        long begin = System.currentTimeMillis();
        List<String> l1 = new ArrayList<>(10000);
               for (int i = 0; i < 10000; i++) {
            String key = "test1_" + i;
            String s = jedis.get(key);
            l1.add(s);
        }
        System.out.println("sync:" + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();

        Map<String, Response<String>> responseMap = new HashMap<>(1000);
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            String key = "test1_" + i;
            Response<String> response = pipelined.get(key);
            responseMap.put(key, response);
        }
        pipelined.sync();
        System.out.println("async:" + (System.currentTimeMillis() - begin));


        System.out.println(l1.get(100) + ":" + responseMap.get("test1_100").get());
    }

    public void mgetTest(Jedis jedis){
        String[] keys = new String[10000];
        for (int i = 0; i < 10000; i++) {
            keys[i] = "test1_" + i;
        }
        long begin = System.currentTimeMillis();
        jedis.mget(keys);
        System.out.println("sync:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();

        Pipeline pipelined = jedis.pipelined();
        Response<List<String>> mget1 = pipelined.mget(keys);
        pipelined.sync();
        mget1.get();
        System.out.println("async:" + (System.currentTimeMillis() - begin));

    }
}
