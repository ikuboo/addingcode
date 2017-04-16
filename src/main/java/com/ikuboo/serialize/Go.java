package com.ikuboo.serialize;

import com.ikuboo.serialize.fastjson.FastjosnReport;
import com.ikuboo.serialize.hession.HessionReport;
import com.ikuboo.serialize.jdk.JdkReport;
import com.ikuboo.serialize.kryo.KryoReport;
import com.ikuboo.serialize.utils.ComplexEntity;
import com.ikuboo.serialize.utils.Report;
import com.ikuboo.serialize.utils.Result;
import com.ikuboo.serialize.utils.TestHelper;

/**
 * Created by Administrator on 2017/4/1 0001.
 *
 */
public class Go {
    public static void main(String[] args) throws Exception {
        int[] loops = {10, 100};
        boolean [] warmups = {false, true};


        ComplexEntity complexEntity = TestHelper.initComplexEntity();

        //Report report = new FastjosnReport();
        //Report report = new JdkReport();
        //Report report = new HessionReport();
        Report report = new KryoReport();

        for (int i = 0; i < loops.length; i++) {
            for (int j = 0; j < warmups.length; j++) {
                Result result = report.doReport(complexEntity, loops[i], warmups[j]);
                System.out.println("loop:" + loops[i] + ";" + "warmup:" + warmups[j]);
                System.out.println("serializeTime:" + result.getSerializeTime() +
                        ";serializeLen:" + result.getSerializeLen() +
                        ";deserializeTime:" + result.getDeserializeTime());
            }
        }



    }
}
