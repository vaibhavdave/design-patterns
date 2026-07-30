package com.designpatterns.creational.builder;

import java.time.Duration;

public final class BuilderDemo {

    public static void main(String[] args) {
        System.out.println("== Fluent nested builder (Effective Java Item 2) ==");
        HttpRequest request = new HttpRequest.Builder()
                .method("POST")
                .url("https://api.example.com/orders")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token123")
                .body("{\"item\":\"widget\"}")
                .timeout(Duration.ofSeconds(10))
                .build();
        System.out.println(request);

        System.out.println();
        System.out.println("== Classic GoF Director/Builder ==");
        Waiter waiter = new Waiter();
        System.out.println("Veg meal:     " + waiter.construct(new VegMealBuilder()));
        System.out.println("Non-veg meal: " + waiter.construct(new NonVegMealBuilder()));
    }
}
