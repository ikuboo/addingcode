package com.ikuboo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用Demo
 */
public class PhantomReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        //new 一个String对象
        String str = new String("Hello,everyone");
        //创建一个引用队列
        ReferenceQueue<String> rq = new ReferenceQueue<>();
        //创建一个虚引用指向到字符串"Hello,everyony"
        PhantomReference pr = new PhantomReference(str, rq);

        //不能通过虚引用访问所引用对象，所以会打印null
        System.out.println("通过虚引用获取对象为:" + pr.get());//①

        //切断str 与 "Hello,everyony" 的关联
        str = null;

        //强制执行GC
        System.gc();
        System.runFinalization();
        //因为GC的不确定性，所以GC后睡眠一会
        TimeUnit.SECONDS.sleep(5);

        //取出引用队列中最先放入队列中的PhantomReference引用与pr对比
        Reference<? extends String> poll = rq.poll();
        System.out.println("" + (poll == pr));//②
    }
}
