package com.ikuboo.guava.event_bus;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class OrderEvent {
    private String message;

    public OrderEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
