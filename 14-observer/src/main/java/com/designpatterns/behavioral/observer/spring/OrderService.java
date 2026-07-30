package com.designpatterns.behavioral.observer.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Publishes an {@link OrderStatusChangedEvent} through {@link ApplicationEventPublisher} instead
 * of iterating a hand-maintained list of observers. It has zero knowledge of who — if anyone —
 * is listening.
 */
@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void changeStatus(String orderId, String newStatus) {
        publisher.publishEvent(new OrderStatusChangedEvent(this, orderId, newStatus));
    }
}
