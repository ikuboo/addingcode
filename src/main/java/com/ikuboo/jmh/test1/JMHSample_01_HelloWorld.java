package com.ikuboo.jmh.test1;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

/**
 * Created by yuanchunsen on 2017/5/18.
 *
 */
public class JMHSample_01_HelloWorld {
    @GenerateMicroBenchmark
    public void wellHelloThere() throws InterruptedException {
        System.out.println("JMHSample_01_HelloWorld.wellHelloThere");
        Thread.sleep(2000);
    }
}

