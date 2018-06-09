package com.ikuboo.jdk.timeunit;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo {
    public static void main(String[] args) {
        System.out.println(String.format("1秒等于{%d}毫秒",TimeUnit.SECONDS.toMillis(1)));
        System.out.println(String.format("1毫秒等于{%d}微秒",TimeUnit.MILLISECONDS.toMicros(1)));
        System.out.println(String.format("1微秒等于{%d}纳秒",TimeUnit.MICROSECONDS.toNanos(1)));
        System.out.println(String.format("一分钟等于%d秒",TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)));    }
}