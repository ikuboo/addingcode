package com.ikuboo.easy.exception;

/**
 * @author yuanchunsen@jd.com
 * 2018/5/10.
 */
public class Test {

    public static void main(String[] args) {
        try {
            testException();
        } catch (Exception e) {

            System.out.println(e.getClass());
            if(e.getCause() != null){
                System.out.println(e.getCause().getClass());
            }


        }
    }


    public static void testException() throws Exception {
        try{
            throw new E1();
        } catch (E1 e1) {
            throw new E2(e1);
        }
    }
}
