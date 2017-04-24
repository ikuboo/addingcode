package com.ikuboo.spring.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yuanchunsen on 2017/4/24.
 *
 */

@Component
public class Task1 {
    private static final Logger logger = LoggerFactory.getLogger(Task1.class);
    @Scheduled(fixedDelay = 5000)
    public void doSomething() throws InterruptedException {
         logger.debug("Task1.doSomething");
         Thread.sleep(3000);
    }
}
