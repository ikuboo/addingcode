package com.ikuboo.multithread.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger 测试
 * Created by Administrator on 2018/3/22 0022.
 */
public class TestExchanger {
    private static final Logger logger = LoggerFactory.getLogger(TestExchanger.class);
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    String exchange = exchanger.exchange("return 1");
                    logger.info("thread1:" + exchange );


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    String exchange = exchanger.exchange("return 2");
                    logger.info("thread2:" + exchange );


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    String exchange = exchanger.exchange("return 3");
                    logger.info("thread3:" + exchange );


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    String exchange = exchanger.exchange("return 4");
                    logger.info("thread4:" + exchange );


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
