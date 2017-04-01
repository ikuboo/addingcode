package com.ikuboo.serialize.hession;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.ikuboo.serialize.utils.ComplexEntity;
import com.ikuboo.serialize.utils.SimpleEntity;
import com.ikuboo.serialize.utils.TestHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class HessionTest {
    public static void main(String[] args) throws IOException {
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
