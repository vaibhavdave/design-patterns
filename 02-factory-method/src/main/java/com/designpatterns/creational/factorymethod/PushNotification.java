package com.designpatterns.creational.factorymethod;

public final class PushNotification implements Notification {

    @Override
    public String render(String message) {
        return "[Push] " + message + " · tap to open";
    }
}
