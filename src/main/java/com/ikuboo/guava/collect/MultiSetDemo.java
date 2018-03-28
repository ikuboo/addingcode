package com.ikuboo.guava.collect;

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;

/**
 * 可以获取重复元素个数的set
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class MultiSetDemo {
    public static void main(String[] args) {
        Multiset set = LinkedHashMultiset.create();
        set.add("a");
        set.add("b");
        set.add("c");
        System.out.println(set.size());
        set.add("d",2);//增加2个d
        System.out.println(set.size());
        System.out.println(set.count("d"));
    }
}
