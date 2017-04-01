package com.ikuboo.serialize.utils;

/**
 * Created by yuanchunsen on 2017/4/1.
 * 序列化报告返回结果
 */
public class Result {

    private long serializeTime;
    private int serializeLen;
    private long deserializeTime;

    public Result(long serializeTime, int serializeLen, long deserializeTime) {
        this.serializeTime = serializeTime;
        this.serializeLen = serializeLen;
        this.deserializeTime = deserializeTime;
    }

    public long getSerializeTime() {
        return serializeTime;
    }

    public void setSerializeTime(long serializeTime) {
        this.serializeTime = serializeTime;
    }

    public int getSerializeLen() {
        return serializeLen;
    }

    public void setSerializeLen(int serializeLen) {
        this.serializeLen = serializeLen;
    }

    public long getDeserializeTime() {
        return deserializeTime;
    }

    public void setDeserializeTime(long deserializeTime) {
        this.deserializeTime = deserializeTime;
    }
}
