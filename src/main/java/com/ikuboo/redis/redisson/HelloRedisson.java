package com.ikuboo.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * redission 简单测试
 */
public class HelloRedisson {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        String key = "test_key";
        RBucket<Object> result = redisson.getBucket(key);
        if (!result.isExists()) {
            result.set("test_value", 5, TimeUnit.MINUTES);
        }


        result = redisson.getBucket("test_key");
        System.out.println(result.get().toString());

    }
}
