package com.ikuboo.lru;

import java.util.LinkedHashMap;

public class LinkedMap{
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>(2, 0.75f, true);
        map.put("1", 1);
        System.out.println(map);
        map.put("2", 2);
        System.out.println(map);
        map.put("3", 3);
        System.out.println(map);

        map.put("2", 22);
        System.out.println(map);
    }
}