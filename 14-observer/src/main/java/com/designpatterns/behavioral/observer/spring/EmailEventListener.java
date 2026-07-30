package com.designpatterns.behavioral.observer.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** Reacts to {@link OrderStatusChangedEvent} without ever being registered on the publisher. */
@Component
public class EmailEventListener {

    private final List<String> sentEmails = new ArrayList<>();

    @EventListener
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        sentEmails.add("Email to customer: order %s is now %s"
                .formatted(event.getOrderId(), event.getNewStatus()));
    }

    public List<String> getSentEmails() {
        return sentEmails;
    }
}
