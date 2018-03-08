package com.ikuboo.threadlocal;

import java.util.HashMap;


/**
 * @author yuanchunsen@jd.com
 *         2017/12/26.
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("key","value");
        System.out.println(1 << 30);


        int MAXIMUM_CAPACITY = 1 << 30;
        int number = 16 >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : (16 > 1) ? Integer.highestOneBit((16 - 1) << 1) : 1;

        System.out.println(number);

    }
}
