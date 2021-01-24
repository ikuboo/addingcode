package com.ikuboo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU 基于LinkedHashMap
 * @param <K>
 * @param <V>
 */
public class LruCache1<K, V> extends LinkedHashMap<K, V> {
    private Integer size;
    public LruCache1(Integer size){
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        //插入新元素会调用次方法
        return size() > size;
    }

    public static void main(String[] args) {
        LruCache1<String, String> lruCache1 = new LruCache1(3);
        lruCache1.put("key1","value1");
        System.out.println(lruCache1);
        lruCache1.put("key2","value2");
        System.out.println(lruCache1);
        lruCache1.put("key3","value3");
        System.out.println(lruCache1);
        lruCache1.put("key1","value1_1");
        System.out.println(lruCache1);
        lruCache1.put("key4", "value4");
        System.out.println(lruCache1);
    }

}
