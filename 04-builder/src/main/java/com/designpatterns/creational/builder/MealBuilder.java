package com.designpatterns.creational.builder;

/**
 * The classic GoF Builder interface: a fixed set of construction steps, each implemented
 * differently per concrete builder, plus a {@link #build()} that hands back the finished product.
 * Unlike {@link HttpRequest.Builder}, steps here don't return {@code this} — the sequencing is
 * owned by {@link Waiter} (the Director), not by the caller chaining calls.
 */
public interface MealBuilder {

    void addMainCourse();

    void addSide();

    void addDrink();

    Meal build();
}
