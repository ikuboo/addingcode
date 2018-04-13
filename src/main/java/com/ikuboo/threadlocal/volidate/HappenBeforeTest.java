package com.ikuboo.threadlocal.volidate;

/**
 * @author yuanchunsen@jd.com
 *         2018/4/2.
 */
public class HappenBeforeTest {
    private static  int a = 0;
    private static  boolean flag = false;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 100 ; i++){
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    //synchronized (lock){
                        a = 1;

                    flag = true;
                    //}

                }
            });


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 1000; i++){
                        if(flag && a == 0){
                            System.out.println("指令被重排序了");
                        }
                    }


                }
            });

            t2.start();
            t1.start();

            t1.join();
            t2.join();

            a = 0;
            flag = false;
        }
    }
}
