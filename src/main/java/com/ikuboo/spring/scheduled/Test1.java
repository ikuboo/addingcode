package com.ikuboo.spring.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yuanchunsen on 2017/4/24.
 *
 */
public class Test1 {

    private static final Logger logger = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        synchronized (Test1.class) {
           Test1.class.wait();
        }
    }

}
