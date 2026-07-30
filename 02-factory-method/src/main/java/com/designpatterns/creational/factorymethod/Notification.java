package com.designpatterns.creational.factorymethod;

/**
 * The Product interface. Every channel formats and "sends" a message differently, but the
 * creator code that drives the send never needs to know which one it's holding.
 */
public interface Notification {

    /**
     * Formats {@code message} the way this channel would actually deliver it, and returns the
     * rendered payload (in a real system this would also perform the network call).
     */
    String render(String message);
}
