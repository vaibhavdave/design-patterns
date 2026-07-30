package com.designpatterns.creational.factorymethod;

public final class PushNotificationFactory extends NotificationFactory {

    @Override
    protected Notification createNotification() {
        return new PushNotification();
    }
}
