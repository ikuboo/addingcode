package com.ikuboo.guava.ratelimiter;

import com.google.common.base.Ticker;

public class TickerDemo {
    public static void main(String[] args) {
        Ticker ticker = Ticker.systemTicker();
        long read = ticker.read();
        long read1 = ticker.read();
        long read2 = ticker.read();
        long read3 = ticker.read();

        System.out.println(read);
        System.out.println(read1);
        System.out.println(read2);
        System.out.println(read3);
    }
}
