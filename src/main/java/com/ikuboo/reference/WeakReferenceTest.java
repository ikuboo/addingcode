package com.ikuboo.reference;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用Demo
 */
public class WeakReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        //new 一个String对象
        String str = new String("Hello,everyone");//①
        //创建一个弱引用指向到字符串"Hello,everyony"
        WeakReference<String> wr = new WeakReference<>(str);//②
        //切断str 与 "Hello,everyony" 的关联
        str = null;//③

        System.out.println("GC前:" + wr.get());
        //强制执行GC
        System.gc();
        System.runFinalization();
        //因为GC的不确定性，所以GC后睡眠一会
        TimeUnit.SECONDS.sleep(5);
        System.out.println("GC前:" + wr.get());
    }
}
