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
public class Task3 {
    private static final Logger logger = LoggerFactory.getLogger(Task3.class);
    @Scheduled(cron = "*/1 * * * * *")
    public void doSomething() throws InterruptedException {
         logger.debug("Task4.doSomething");
         Thread.sleep(5000);
    }
}
