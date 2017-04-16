package com.ikuboo.serialize.utils;

import java.io.IOException;

/**
 * Created by yuanchunsen on 2017/4/1.
 *
 */
public interface Report {
    /**
     * 获取 序列化报告
     * @param entity 被序列画的对象
     * @param loop 循环的次数，取平均值
     * @param warmup true：预热，false：不预热
     * @return
     */
    Result doReport (ComplexEntity entity, int loop, boolean warmup) throws Exception;
}
