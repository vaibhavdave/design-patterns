package com.designpatterns.enterprise.specification;

import java.util.List;

public class SpecificationDemo {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1500.0, 10, true),   // rush + high-value
                new Order(300.0, 30, false),    // large, not high-value
                new Order(800.0, 4, false),     // moderate
                new Order(50.0, 1, true),       // express but small
                new Order(2000.0, 50, false)    // high-value + large
        );

        System.out.println("=== Eligible for discount ===");
        orders.stream()
                .filter(OrderSpecification.isEligibleForDiscount()::isSatisfiedBy)
                .forEach(o -> System.out.printf("  Order $%.2f, %d items, express=%s%n",
                        o.amount(), o.itemCount(), o.isExpress()));

        System.out.println("\n=== Rush orders ===");
        orders.stream()
                .filter(OrderSpecification.isRushOrder()::isSatisfiedBy)
                .forEach(o -> System.out.printf("  Order $%.2f, %d items%n",
                        o.amount(), o.itemCount()));

        System.out.println("\n=== Standard express (express but not rush) ===");
        orders.stream()
                .filter(OrderSpecification.isStandardExpress()::isSatisfiedBy)
                .forEach(o -> System.out.printf("  Order $%.2f, %d items%n",
                        o.amount(), o.itemCount()));
    }
}
