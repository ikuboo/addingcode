package com.ikuboo.guava.event_bus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * 如果EventBus发送的消息都不是订阅者关心的称之为Dead Event
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class DeadEventListener {
    boolean isDelivered=true;

    @Subscribe
    public void listen(DeadEvent event){
        isDelivered=false;
        System.out.println(event.getSource().getClass()+"  "+event.getEvent()); //source通常是EventBus
    }

    public boolean isDelivered() {
        return isDelivered;
    }
}
