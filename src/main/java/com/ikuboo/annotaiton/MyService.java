package com.ikuboo.annotaiton;

import com.ikuboo.annotaiton.ann.AutoConfig;

public class MyService {
    @AutoConfig(key = "key123")
    private volatile String keyValue;

    public void print(){
        System.out.println(keyValue);
    }
}
