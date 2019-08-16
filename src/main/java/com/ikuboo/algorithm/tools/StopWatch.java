package com.ikuboo.algorithm.tools;

public class StopWatch {
    private final long start;

    public StopWatch(){
        start = System.currentTimeMillis();
    }

    public long elapsedTime(){
        return System.currentTimeMillis() - start;
    }
}
