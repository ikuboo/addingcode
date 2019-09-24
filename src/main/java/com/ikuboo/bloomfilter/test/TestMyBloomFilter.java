package com.ikuboo.bloomfilter.test;

import com.ikuboo.bloomfilter.MyBloomFilter;

public class TestMyBloomFilter {
    public static void main(String[] args) {
        int capacity = 10000000;
        MyBloomFilter bloomFilters = new MyBloomFilter(capacity);
        bloomFilters.put("key1");

        System.out.println("key1是否存在:" + bloomFilters.exist("key1"));
        System.out.println("key2是否存在:" + bloomFilters.exist("key2"));
    }
}
