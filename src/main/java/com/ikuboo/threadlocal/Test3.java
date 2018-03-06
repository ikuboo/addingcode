package com.ikuboo.threadlocal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuanchunsen@jd.com
 *         2018/2/24.
 */
public class Test3 {
    public static void main(String[] args) {
        List<Long>  a  = new LinkedList<>();
        a.add(1L);
        a.add(2L);
        a.add(3L);
        a.add(5L);

        List<Long>  b = new LinkedList<>(a);

        for(Long i = 1L;  i < 3L; i++){
            b.remove(i);
        }



        System.out.println(a);
        System.out.println(b);
    }
}
