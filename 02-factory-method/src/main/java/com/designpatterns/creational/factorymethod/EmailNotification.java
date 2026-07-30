package com.designpatterns.creational.factorymethod;

public final class EmailNotification implements Notification {

    @Override
    public String render(String message) {
        return "[Email] Subject: Notification\n" + message;
    }
}
