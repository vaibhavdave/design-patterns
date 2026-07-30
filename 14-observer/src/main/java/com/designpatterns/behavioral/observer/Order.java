package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Subject. It owns the list of interested {@link OrderObserver}s directly — this is the part
 * a Spring {@code ApplicationEventPublisher} replaces (see the {@code spring} subpackage):
 * here, {@code Order} must hold a reference to every observer it notifies.
 */
public final class Order {

    private final String id;
    private final List<OrderObserver> observers = new ArrayList<>();
    private String status = "CREATED";

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers(newStatus);
    }

    private void notifyObservers(String newStatus) {
        for (OrderObserver observer : observers) {
            observer.onStatusChanged(this, newStatus);
        }
    }
}
