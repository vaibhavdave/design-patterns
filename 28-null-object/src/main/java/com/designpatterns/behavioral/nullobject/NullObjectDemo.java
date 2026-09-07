package com.designpatterns.behavioral.nullobject;

public class NullObjectDemo {

    public static void main(String[] args) {
        System.out.println("=== With ConsoleLogger (real output) ===");
        OrderService verbose = new OrderService(new ConsoleLogger("OrderService"));
        String id = verbose.placeOrder("Widget", 3);
        verbose.placeOrder("Gadget", 0);
        verbose.cancelOrder(id);

        System.out.println();
        System.out.println("=== With NullLogger (silent — no output) ===");
        OrderService silent = new OrderService(NullLogger.INSTANCE);
        silent.placeOrder("Widget", 5);   // no output at all — no null checks needed
        silent.placeOrder("Gadget", -1);  // same
    }
}
