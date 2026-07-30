package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/** Stand-in for shipping status events to an analytics pipeline; records calls for verification. */
public final class AnalyticsObserver implements OrderObserver {

    private final List<String> recordedEvents = new ArrayList<>();

    @Override
    public void onStatusChanged(Order order, String newStatus) {
        recordedEvents.add(order.getId() + ":" + newStatus);
    }

    public List<String> getRecordedEvents() {
        return recordedEvents;
    }
}
