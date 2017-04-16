package com.ikuboo.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.ikuboo.serialize.utils.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class KryoReport implements Report {

    public Result doReport(ComplexEntity entity, int loop, boolean warmup) throws Exception {
        long serializeTimeTotal = 0;
        long deserializeTimeTotal = 0;
        int serializeLen = 0;

        Kryo kryo = new Kryo();
        kryo.register(SimpleEntity.class);
        kryo.register(ComplexEntity.class);

        //预热
        if(warmup){
            Output output = new Output(10000,5000000);
            kryo.writeObject(output, entity);
            output.close();
            byte[] buffer = output.getBuffer();

            Input input = new Input(buffer);
            ComplexEntity complexEntity = kryo.readObject(input, ComplexEntity.class);
            input.close();
        }


        for (int i = 0; i < loop; i++) {
            long begin = System.currentTimeMillis();
            Output output1 = new Output(10000,5000000);
            kryo.writeObject(output1, entity);
            output1.close();
            serializeTimeTotal += System.currentTimeMillis() - begin;
            byte[] buffer1 = output1.getBuffer();

            begin = System.currentTimeMillis();
            Input input1 = new Input(buffer1);
            ComplexEntity complexEntity = kryo.readObject(input1, ComplexEntity.class);
            input1.close();
            deserializeTimeTotal += System.currentTimeMillis() - begin;
            serializeLen += buffer1.length;
        }
        List<String> l = new ArrayList<String>(1);
        Iterator<String> iterator = l.iterator();
        return new Result(serializeTimeTotal / loop, serializeLen / loop, deserializeTimeTotal / loop);
    }
}
