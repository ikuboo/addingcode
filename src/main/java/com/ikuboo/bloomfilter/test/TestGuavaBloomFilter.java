package com.ikuboo.bloomfilter.test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class TestGuavaBloomFilter {
    public static void main(String[] args) {
        //预估的容量
        int capacity = 10000000;
        //期望的错误率
        double fpp = 0.01;

        BloomFilter<String> bloomFilters = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")), capacity, fpp);

        bloomFilters.put("key1");

        System.out.println("key1是否存在:" + bloomFilters.mightContain("key1"));
        System.out.println("key2是否存在:" + bloomFilters.mightContain("key2"));
    }
}
