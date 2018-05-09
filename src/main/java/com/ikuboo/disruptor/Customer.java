package com.ikuboo.disruptor;


import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

public class Customer implements EventHandler<StringEvent> {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(StringEvent event, long l, boolean b) throws Exception {

        TimeUnit.SECONDS.sleep(1);
        System.out.println(name +":" + event.getValue());
    }
}
