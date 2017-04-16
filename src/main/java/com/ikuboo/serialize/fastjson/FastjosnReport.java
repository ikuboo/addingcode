package com.ikuboo.serialize.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ikuboo.serialize.utils.*;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class FastjosnReport implements Report {

    public Result doReport(ComplexEntity entity, int loop, boolean warmup) {
        long serializeTimeTotal = 0;
        long deserializeTimeTotal = 0;
        int serializeLen = 0;

        //预热
        if(warmup){
            String jsonStr = JSON.toJSONString(entity);
            ComplexEntity obj = JSONObject.parseObject(jsonStr, ComplexEntity.class);
        }


        for (int i = 0; i < loop; i++) {
            long begin = System.currentTimeMillis();
            String str = JSON.toJSONString(entity);
            serializeTimeTotal += System.currentTimeMillis() - begin;

            begin = System.currentTimeMillis();
            ComplexEntity entity1 = JSONObject.parseObject(str, ComplexEntity.class);
            deserializeTimeTotal += System.currentTimeMillis() - begin;
            serializeLen += str.getBytes().length;
        }

        return new Result(serializeTimeTotal / loop, serializeLen / loop, deserializeTimeTotal / loop);
    }
}
