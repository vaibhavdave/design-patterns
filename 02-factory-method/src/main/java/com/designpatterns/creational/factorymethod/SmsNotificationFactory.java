package com.designpatterns.creational.factorymethod;

public final class SmsNotificationFactory extends NotificationFactory {

    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}
