package com.ikuboo.bloomfilter;

import java.util.BitSet;

/**
 * 布隆过滤器
 */
public class MyBloomFilter {

    private int length;

    /**
     * bitset
     */
    private BitSet bitSet;

    public MyBloomFilter(int length) {
        this.length = length;
        this.bitSet = new BitSet(length);
    }

    /**
     * 写入数据
     */
    public void put(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        bitSet.set(first % length);
        bitSet.set(second % length);
        bitSet.set(third % length);
    }

    /**
     * 判断数据是否存在
     *
     * @param key
     * @return true:存在，false：不存在
     */
    public boolean exist(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        boolean firstIndex = bitSet.get(first % length);
        if (!firstIndex) {
            return false;
        }
        boolean secondIndex = bitSet.get(second % length);
        if (!secondIndex) {
            return false;
        }
        boolean thirdIndex = bitSet.get(third % length);
        if (!thirdIndex) {
            return false;
        }
        return true;
    }

    /**
     * hash 算法1
     */
    private int hashcode_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    /**
     * hash 算法2
     */
    private int hashcode_2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    /**
     * hash 算法3
     */
    private int hashcode_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }

}
