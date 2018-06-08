package com.ikuboo.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;

public class GuavaLimiter {
    private static RateLimiter rateLimiter = RateLimiter.create(1);
    public static void main(String[] args) {
        while (true){
            double acquire = rateLimiter.acquire();
            System.out.println(String.format("%tT  -  acquire=%f", new Date(), acquire));
        }
    }
}
