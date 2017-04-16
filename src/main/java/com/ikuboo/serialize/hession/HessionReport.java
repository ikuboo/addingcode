package com.ikuboo.serialize.hession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.ikuboo.serialize.utils.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class HessionReport implements Report {
    public Result doReport(ComplexEntity entity, int loop, boolean warmup) throws IOException {
        long serializeTimeTotal = 0;
        long deserializeTimeTotal = 0;
        int serializeLen = 0;

        //预热
        if(warmup){
            byte[] serialize = serialize(entity);
            ComplexEntity complexEntity = (ComplexEntity) deserialize(serialize);
        }


        for (int i = 0; i < loop; i++) {
            long begin = System.currentTimeMillis();
            byte[] serialize1 = serialize(entity);
            serializeTimeTotal += System.currentTimeMillis() - begin;

            begin = System.currentTimeMillis();
            ComplexEntity complexEntity1 = (ComplexEntity) deserialize(serialize1);
            deserializeTimeTotal += System.currentTimeMillis() - begin;
            serializeLen += serialize1.length;
        }

        return new Result(serializeTimeTotal / loop, serializeLen / loop, deserializeTimeTotal / loop);
    }

    public static  byte[] serialize(Object obj) throws IOException {
        if(obj==null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray();
    }

    public static Object deserialize(byte[] by) throws IOException{
        if(by==null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }
}
