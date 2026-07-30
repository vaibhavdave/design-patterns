package com.designpatterns.creational.factorymethod;

public final class NotificationDemo {

    public static void main(String[] args) {
        System.out.println("== Factory Method: notification channels ==");
        for (NotificationChannel channel : NotificationChannel.values()) {
            NotificationFactory factory = channel.factory();
            System.out.println(channel + " -> " + factory.send("Your order has shipped."));
        }
    }
}
