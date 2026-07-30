package com.designpatterns.behavioral.observer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void settingStatusNotifiesAllRegisteredObservers() {
        Order order = new Order("ORD-1");
        EmailNotifierObserver emailObserver = new EmailNotifierObserver();
        AnalyticsObserver analyticsObserver = new AnalyticsObserver();
        order.addObserver(emailObserver);
        order.addObserver(analyticsObserver);

        order.setStatus("SHIPPED");

        assertThat(emailObserver.getSentEmails()).containsExactly(
                "Email to customer: order ORD-1 is now SHIPPED");
        assertThat(analyticsObserver.getRecordedEvents()).containsExactly("ORD-1:SHIPPED");
    }

    @Test
    void settingStatusUpdatesTheOrdersOwnState() {
        Order order = new Order("ORD-1");

        order.setStatus("CANCELLED");

        assertThat(order.getStatus()).isEqualTo("CANCELLED");
    }

    @Test
    void removedObserversAreNoLongerNotified() {
        Order order = new Order("ORD-1");
        AnalyticsObserver analyticsObserver = new AnalyticsObserver();
        order.addObserver(analyticsObserver);
        order.removeObserver(analyticsObserver);

        order.setStatus("PAID");

        assertThat(analyticsObserver.getRecordedEvents()).isEmpty();
    }

    @Test
    void multipleStatusChangesAreAllDeliveredInOrder() {
        Order order = new Order("ORD-1");
        AnalyticsObserver analyticsObserver = new AnalyticsObserver();
        order.addObserver(analyticsObserver);

        order.setStatus("PAID");
        order.setStatus("SHIPPED");
        order.setStatus("DELIVERED");

        assertThat(analyticsObserver.getRecordedEvents())
                .containsExactly("ORD-1:PAID", "ORD-1:SHIPPED", "ORD-1:DELIVERED");
    }
}
