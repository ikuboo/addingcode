package com.ikuboo.serialize.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ikuboo.serialize.utils.*;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class FastjosnReport implements Report {
    private String simpleEntities_serialize;

    public static void main(String[] args) {
        SimpleEntity simpleEntity = TestHelper.initSimpleEntity();
        ComplexEntity complexEntity = TestHelper.initComplexEntity();

        //serialize
        long begin = System.currentTimeMillis();
        String simpleEntity_serialize = JSON.toJSONString(simpleEntity);
        long end = System.currentTimeMillis() - begin;
        System.out.println("简单对象序列化\t\ttime:" + end + ";length:" + simpleEntity_serialize.getBytes().length);



        begin = System.currentTimeMillis();
        String complexEntity_serialize = JSON.toJSONString(complexEntity);
        end = System.currentTimeMillis() - begin;
        System.out.println("复杂对象序列化\t\ttime:" + end + ";length:" + complexEntity_serialize.getBytes().length);



        //unserialize
        begin = System.currentTimeMillis();
        SimpleEntity simpleEntity1 = JSONObject.parseObject(simpleEntity_serialize, SimpleEntity.class);
        end = System.currentTimeMillis() - begin;
        System.out.println("简单对象反序列化\t\ttime:" + end);


        begin = System.currentTimeMillis();
        ComplexEntity complexEntity1 = JSONObject.parseObject(complexEntity_serialize, ComplexEntity.class);
        end = System.currentTimeMillis() - begin;
        System.out.println("复杂对象反序列化\t\ttime:" + end);



    }

    public Result doReport(ComplexEntity entity, int loop) {


        long begin = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {

        }
        long end = System.currentTimeMillis() - begin;



        return null;
    }
}
