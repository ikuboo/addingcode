package com.ikuboo.spring.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yuanchunsen on 2017/4/24.
 *
 */

@Component
public class Task2 {
    private static final Logger logger = LoggerFactory.getLogger(Task1.class);

    public void doSomething() {
       logger.debug("Task2.doSomething");
    }
}
