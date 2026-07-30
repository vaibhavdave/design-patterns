package com.designpatterns.creational.factorymethod;

public final class SmsNotification implements Notification {

    private static final int MAX_LENGTH = 160;

    @Override
    public String render(String message) {
        String truncated = message.length() > MAX_LENGTH ? message.substring(0, MAX_LENGTH) : message;
        return "[SMS] " + truncated;
    }
}
