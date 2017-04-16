package com.ikuboo.serialize.jdk;

import com.ikuboo.serialize.utils.*;

import java.io.*;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class JdkReport implements Report {

    public Result doReport(ComplexEntity entity, int loop, boolean warmup) throws Exception {
        long serializeTimeTotal = 0;
        long deserializeTimeTotal = 0;
        int serializeLen = 0;

        //预热
        if(warmup){
            byte[] serialize = serialize(entity);
            ComplexEntity simpleEntity1 = (ComplexEntity) deserialize(serialize);
        }


        for (int i = 0; i < loop; i++) {
            long begin = System.currentTimeMillis();
            byte[] serialize1 = serialize(entity);
            serializeTimeTotal += System.currentTimeMillis() - begin;

            begin = System.currentTimeMillis();
            ComplexEntity simpleEntity1 = (ComplexEntity) deserialize(serialize1);
            deserializeTimeTotal += System.currentTimeMillis() - begin;
            serializeLen += serialize1.length;
        }

        return new Result(serializeTimeTotal / loop, serializeLen / loop, deserializeTimeTotal / loop);
    }

    public static byte[] serialize(Object obj) throws Exception {
        if(obj==null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(obj);
        return os.toByteArray();
    }

    public static Object deserialize(byte[] by) throws Exception {
        if(by==null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        ObjectInputStream in = new ObjectInputStream(is);
        return in.readObject();
    }
}
