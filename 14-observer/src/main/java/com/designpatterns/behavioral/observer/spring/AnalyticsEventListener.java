package com.designpatterns.behavioral.observer.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** A second, independent listener on the same event — no coordination with {@link EmailEventListener}. */
@Component
public class AnalyticsEventListener {

    private final List<String> recordedEvents = new ArrayList<>();

    @EventListener
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        recordedEvents.add(event.getOrderId() + ":" + event.getNewStatus());
    }

    public List<String> getRecordedEvents() {
        return recordedEvents;
    }
}
