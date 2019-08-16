package com.ikuboo.guava;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson2 {

    public static void main(String[] args) {
//        String json = "{110100:\"北京\",310100:\"上海\"}";
//
//        JSONObject jsonObject = JSON.parseObject(json);
//
//        List<City> cities = new ArrayList<>();
//
//        for(Map.Entry<String, Object>  entry : jsonObject.entrySet()){
//            try{
//                City c  = new City();
//                c.id = Long.valueOf(entry.getKey());
//                c.name = entry.getValue().toString();
//
//                cities.add(c);
//            }catch (Exception e){
//
//            }
//        }
//        System.out.println(cities);

        Object s = null;

        Logger aaa = LoggerFactory.getLogger("aaa");
        aaa.error("{}", s);
    }

    static class City{
        public Long id;
        public String name;
    }
}