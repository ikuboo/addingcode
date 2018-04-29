package com.ikuboo.disruptor;

/**
 * 用于传输数据
 */
public class StringEvent{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
