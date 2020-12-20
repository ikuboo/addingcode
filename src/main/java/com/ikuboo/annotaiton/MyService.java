package com.ikuboo.annotaiton;

public class MyService {
    @AutoConfig(key = "key123")
    private volatile String keyValue;

    public void print(){
        System.out.println(keyValue);
    }
}
