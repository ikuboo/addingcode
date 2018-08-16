package com.ikuboo.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用Demo
 */
public class ReferenceClearTest {
    public static void main(String[] args) throws InterruptedException {
        //new 一个String对象
        String str = new String("Hello,everyone");//①
        //创建一个软引用指向到字符串"Hello,everyony"
        SoftReference<String> sr = new SoftReference<>(str);//②
        //切断str 与 "Hello,everyony" 的关联
        str = null;//③

        //强制执行GC
        System.gc();
        System.runFinalization();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(sr.get());

        sr.clear();

        //强制执行GC
        System.gc();
        System.runFinalization();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(sr.get());
    }
}
