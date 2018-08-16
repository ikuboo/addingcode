package com.ikuboo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPrintLog {
    private static final Logger logger = LoggerFactory.getLogger(TestPrintLog.class);

    public static void main(String[] args) {
        A a = new A();

        try
        {
            a.a();
        }catch (Exception e){
            logger.warn("haha,p={}",1,e);
        }
        logger.error("error,p={},p2 = {}",1,2);
    }
}
class A {
    public void a(){
        B b = new B();
        b.b();
    }
}
class B{
    public void b(){
        int a = 0;
        int b = 2 /a;
    }
}
