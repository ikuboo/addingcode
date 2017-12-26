package com.ikuboo.objectsize;

import java.lang.instrument.Instrumentation;

/**
 * @author yuanchunsen@jd.com
 *         2017/12/19.
 */
public class MySizeOf {
    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation instP){
        instrumentation = instP;
    }

    public static long sizeOf(Object o){
        if(null == instrumentation){
            throw new IllegalStateException("Can not access instrumentation ");
        }
        return instrumentation.getObjectSize(o);
    }
}
