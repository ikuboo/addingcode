package com.ikuboo.serialize.jdk;

import com.ikuboo.serialize.utils.ComplexEntity;
import com.ikuboo.serialize.utils.SimpleEntity;
import com.ikuboo.serialize.utils.TestHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class JdkTest {

    public static void main(String[] args) throws Exception {
        SimpleEntity simpleEntity = TestHelper.initSimpleEntity();
        ComplexEntity complexEntity = TestHelper.initComplexEntity();

        //serialize
        long begin = System.currentTimeMillis();
        byte[] serialize = serialize(simpleEntity);
        long end = System.currentTimeMillis() - begin;
        System.out.println("简单对象序列化\t\ttime:" + end + ";length:" + serialize.length);



        begin = System.currentTimeMillis();
        byte[] serialize1 = serialize(complexEntity);
        end = System.currentTimeMillis() - begin;
        System.out.println("复杂对象序列化\t\ttime:" + end + ";length:" + serialize1.length);



        //unserialize
        begin = System.currentTimeMillis();
        SimpleEntity simpleEntity1 = (SimpleEntity) deserialize(serialize);
        end = System.currentTimeMillis() - begin;
        System.out.println("简单对象反序列化\t\ttime:" + end);


        begin = System.currentTimeMillis();
        ComplexEntity complexEntity1 = (ComplexEntity) deserialize(serialize1);
        end = System.currentTimeMillis() - begin;
        System.out.println("复杂对象反序列化\t\ttime:" + end);
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
