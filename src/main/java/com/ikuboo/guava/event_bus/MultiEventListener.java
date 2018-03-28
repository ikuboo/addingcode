package com.ikuboo.guava.event_bus;

import com.google.common.eventbus.Subscribe;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class MultiEventListener {
    @Subscribe
    public void listen(OrderEvent event){
        System.out.println("receive msg: "+event.getMessage());
    }

    @Subscribe
    public void listen(String message){
        System.out.println("receive msg: "+message);
    }
}
