package com.ikuboo.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.ikuboo.serialize.utils.ComplexEntity;
import com.ikuboo.serialize.utils.SimpleEntity;
import com.ikuboo.serialize.utils.TestHelper;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class KryoTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        SimpleEntity simpleEntity = TestHelper.initSimpleEntity();
        ComplexEntity complexEntity = TestHelper.initComplexEntity();

        Kryo kryo = new Kryo();

        kryo.register(SimpleEntity.class);
        kryo.register(ComplexEntity.class);


        long begin = System.currentTimeMillis();
        Output output1 = new Output(10,1000);
        kryo.writeObject(output1, simpleEntity);
        output1.close();
        byte[] buffer1 = output1.getBuffer();
        long end = System.currentTimeMillis() - begin;
        System.out.println("简单对象序列化\t\ttime:" + end + ";length:" + buffer1.length);


        begin = System.currentTimeMillis();
        Output output3 = new Output(10,1370107);
        kryo.writeObject(output3, complexEntity);
        output3.close();
        byte[] buffer3 = output3.getBuffer();
        end = System.currentTimeMillis() - begin;
        System.out.println("复杂对象序列化\t\ttime:" + end + ";length:" + buffer3.length);

        //unserialize
        begin = System.currentTimeMillis();
        Input input1 = new Input(buffer1);
        SimpleEntity simpleEntity1 = kryo.readObject(input1, SimpleEntity.class);
        input1.close();
        end = System.currentTimeMillis() - begin;
        System.out.println("简单对象反序列化\t\ttime:" + end);

        begin = System.currentTimeMillis();
        Input input3 = new Input(buffer3);
        ComplexEntity complexEntity1 = kryo.readObject(input3, ComplexEntity.class);
        input3.close();
        end = System.currentTimeMillis() - begin;
        System.out.println("复杂对象反序列化\t\ttime:" + end);
    }
}
