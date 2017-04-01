package com.ikuboo.serialize.utils;

import java.util.*;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public class TestHelper {

    public static SimpleEntity initSimpleEntity(){

        SimpleEntity simpleEntity = new SimpleEntity();


        simpleEntity.setFboolean(new Random().nextBoolean());
        simpleEntity.setFdouble(new Random().nextDouble());
        simpleEntity.setFfloat(new Random().nextFloat());
        simpleEntity.setFint(new Random().nextInt());
        simpleEntity.setFinteger(new Random().nextInt());
        simpleEntity.setFlong(new Random().nextLong());
        simpleEntity.setFstring(System.nanoTime() + "");
        simpleEntity.setFstringnull(null);


        return simpleEntity;
    }

    public static SimpleEntity[] initSimpleEntity(Integer initSize){
        SimpleEntity[] arr = new SimpleEntity[initSize];

        for (int i = 0; i < initSize; i++) {
            arr[i] = initSimpleEntity();
        }
        return arr;
    }

    public static ComplexEntity initComplexEntity(){

        ComplexEntity complexEntity = new ComplexEntity();
        complexEntity.setfSimpleEntity(initSimpleEntity());

        //---------------------fset------------------------------------
        Set<SimpleEntity> fset = new HashSet<SimpleEntity>(1000);
        for (int i = 0; i < 1000; i++) {
            fset.add(initSimpleEntity());
        }
        complexEntity.setFset(fset);

        //---------------------flist-----------------------------------
        List<SimpleEntity> flist = new ArrayList<SimpleEntity>(10000);
        for (int i = 0; i < 1000; i++) {
            flist.add(initSimpleEntity());
        }
        complexEntity.setFlist(flist);

        //---------------------fmap------------------------------------
        Map<String, SimpleEntity> fmap = new HashMap<String, SimpleEntity>(1000);
        for (int i = 0; i < 5000; i++) {
            fmap.put("key:" + i, initSimpleEntity());
        }
        complexEntity.setFmap(fmap);

        //----------------------fobjectmap--------------------------------
        Map<Object, Object> mObject = new HashMap<Object,Object>();

        for(int i = 1; i <= 10; i ++){
            String mapKey;
            Map<String, SimpleEntity> mapValue = new HashMap<String, SimpleEntity>();
            for(int j = 1; j <= 100; j++){
                mapKey = "mapKey:" + i + "-" + j;
                mapValue.put(mapKey, initSimpleEntity());
            }
            mapKey = "mapkey:" + i;
            mObject.put(mapKey, mapValue);
        }

        complexEntity.setFobjectmap(mObject);

        return complexEntity;
    }

    public static ComplexEntity[] initComplexEntity(Integer initSize){
        ComplexEntity[] arr = new ComplexEntity[initSize];

        for (int i = 0; i < initSize; i++) {
            arr[i] = initComplexEntity();
        }
        return arr;
    }
}
