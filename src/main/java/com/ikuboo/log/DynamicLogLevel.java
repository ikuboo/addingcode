package com.ikuboo.log;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * 动态修改日志的级别
 */
public class DynamicLogLevel {
    public static void main(String[] args) throws IOException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    C1.printLog();
                    C2.printLog();
                    System.out.println("------------------------------------------------------------");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            //input like : com.ikuboo.log.C1#error
            //input like : com.ikuboo.log.C2#info
            //input like : all#info
            String input = br.readLine();
            if("exit".equalsIgnoreCase(input)){
                break;
            }
            String[] split = input.split("#");
            if(split.length != 2){
                System.err.println("input error!");
            }
            if("all".equalsIgnoreCase(split[0])){
                changeAllLogLeves(split[1]);
            }else {
                changeLogLevel(split[0], split[1]);
            }
        }
        System.out.println("exit");

    }

    public static void changeLogLevel(String clazzName, String level){
        org.apache.log4j.Logger logger = LogManager.getLogger(clazzName);
        logger.setLevel(Level.toLevel(level));
    }

    public static void changeAllLogLeves(String level){
        Enumeration enumeration = LogManager.getCurrentLoggers();
        while (enumeration.hasMoreElements()){
          org.apache.log4j.Logger logger = (org.apache.log4j.Logger) enumeration.nextElement();
          logger.setLevel(org.apache.log4j.Level.toLevel(level));
        }
    }
}
class C1{
    private static final Logger log = LoggerFactory.getLogger(C1.class);
    public static void printLog(){
        log.debug("C1 debug log");
        log.info("C1 info log");
        log.error("C1 error log");
    }
}

class C2{
    private static final Logger log = LoggerFactory.getLogger("com.ikuboo.log.C2");
    public static void printLog(){
        log.debug("C2 debug log");
        log.info("C2 info log");
        log.error("C2 error log");
    }
}
