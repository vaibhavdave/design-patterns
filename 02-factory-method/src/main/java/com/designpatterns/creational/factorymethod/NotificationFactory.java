package com.designpatterns.creational.factorymethod;

/**
 * The Creator. {@link #send} is the fixed part of the algorithm — it decides *when* logging
 * happens and *that* the rendered payload gets dispatched — while {@link #createNotification()}
 * is the one step subclasses are free to vary. Client code that only holds a
 * {@code NotificationFactory} reference never has to know whether it's talking to the email, SMS
 * or push concrete creator; that's what distinguishes this from a plain {@code if/else new Foo()}
 * "simple factory", where the caller (or the factory's single method) still has a big switch over
 * every concrete type in one place.
 */
public abstract class NotificationFactory {

    /**
     * The factory method. Each concrete creator overrides this to return its own product; nothing
     * else in this class changes.
     */
    protected abstract Notification createNotification();

    /**
     * The template step: uses the product created by {@link #createNotification()} without ever
     * naming a concrete {@link Notification} type.
     */
    public final String send(String message) {
        Notification notification = createNotification();
        String payload = notification.render(message);
        return "Dispatched -> " + payload;
    }
}
