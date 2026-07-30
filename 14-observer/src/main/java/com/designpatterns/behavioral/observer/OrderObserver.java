package com.designpatterns.behavioral.observer;

/**
 * The Observer contract. {@link Order} (the Subject) holds a list of these and calls
 * {@code onStatusChanged} on every registered observer whenever its status changes — the
 * observers never poll the order for changes, they're pushed to.
 */
public interface OrderObserver {

    void onStatusChanged(Order order, String newStatus);
}
