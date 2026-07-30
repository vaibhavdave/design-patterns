package com.designpatterns.behavioral.observer.spring;

import org.springframework.context.ApplicationEvent;

/**
 * A traditional Spring application event. Publishers ({@link OrderService}) and listeners
 * (below) never reference each other directly — both only know about this event type, with the
 * {@code ApplicationContext} acting as the broker between them.
 */
public final class OrderStatusChangedEvent extends ApplicationEvent {

    private final String orderId;
    private final String newStatus;

    public OrderStatusChangedEvent(Object source, String orderId, String newStatus) {
        super(source);
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
