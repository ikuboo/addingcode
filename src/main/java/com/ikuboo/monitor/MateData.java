package com.ikuboo.monitor;

import com.google.common.util.concurrent.AtomicLongMap;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 监控元数据
 */
public class MateData {
    /**
     * 监控时间点
     */
    private long currentTime;
    /**
     * 组调用次数
     */
    private AtomicLongMap<String> clientInvokerCounter;
    /**
     * TP次数,key:ms,value:times.
     */
    private AtomicLongMap<Long> tpTotalCounter;

    /**
     * provider总调用量
     */
    private AtomicLong providerInvokeTotal = new AtomicLong(0);

    private MateData() {}

    public MateData(long currentTime, AtomicLongMap<String> clientInvokerCounter, AtomicLongMap<Long> tpTotalCounter) {
        this.currentTime = currentTime;
        this.clientInvokerCounter = clientInvokerCounter;
        this.tpTotalCounter = tpTotalCounter;
    }


    public long getCurrentTime() {
        return currentTime;
    }

    public AtomicLongMap<String> getClientInvokerCounter() {
        return clientInvokerCounter;
    }

    public AtomicLongMap<Long> getTpTotalCounter() {
        return tpTotalCounter;
    }

    public AtomicLong getProviderInvokeTotal() {
        return providerInvokeTotal;
    }

    @Override
    public String toString() {
        return "MateData{" +
                "tpTotalCounter=" + tpTotalCounter +
                ", clientInvokerCounter=" + clientInvokerCounter +
                ", currentTime=" + currentTime +
                '}';
    }
}