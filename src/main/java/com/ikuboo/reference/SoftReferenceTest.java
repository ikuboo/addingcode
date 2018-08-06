package com.ikuboo.reference;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 软引用Demo
 */
public class SoftReferenceTest {
    static volatile boolean runFlag = true;
    public static void main(String[] args) throws InterruptedException {
        //new 一个String对象
        String str = new String("Hello,everyone");//①
        //创建一个软引用指向到字符串"Hello,everyony"
        SoftReference<String> sr = new SoftReference<>(str);//②
        //切断str 与 "Hello,everyony" 的关联
        str = null;//③

        //启动一个线程，不停的消耗内存
        Thread t = new Thread(new Runnable() {
            List<byte[]> eatMemList = new LinkedList<>();
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        eatMemList.add(new byte[1024 * 1024]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (OutOfMemoryError error){
                        System.out.println("内存不足了..");
                        runFlag = false;
                        break;
                    }
                }
            }
        });
        t.start();

        while (runFlag){
            //强制执行GC
            System.gc();
            System.runFinalization();

            //通过软引用获取字符串,null 表示已经别被回收
            String s = sr.get();//④
            if(null == s){
                System.out.println("字符串被垃圾回收器回收掉了");
            }else {
                System.out.println(s);
            }
        }
    }
}
