package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/** Records every notification it receives so tests can assert on them without mocking I/O. */
public final class EmailNotifierObserver implements OrderObserver {

    private final List<String> sentEmails = new ArrayList<>();

    @Override
    public void onStatusChanged(Order order, String newStatus) {
        sentEmails.add("Email to customer: order %s is now %s".formatted(order.getId(), newStatus));
    }

    public List<String> getSentEmails() {
        return sentEmails;
    }
}
