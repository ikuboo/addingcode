package com.ikuboo.threadlocal.volidate;


//只有读变量的频率特别块的是时候才会出现线程不可见的问题
//取消注释run方法里的Sysout.out 语句会降低读的频率，处理volatile 和普通变量同样的处理逻辑，会及时的写会主存
public class VolatileExample extends Thread {
    private static  Boolean flag = false;
    public void run() {
        while (!flag) {
            //System.out.println("VolatileExample.run");
        }
    }

    public static void main(String[] args) throws Exception {
        new VolatileExample().start();
        Thread.sleep(100);
        flag = true;
    }
}

