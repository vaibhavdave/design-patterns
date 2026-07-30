package com.designpatterns.creational.builder;

/**
 * The Director. Owns the *order* of construction steps (main course, then side, then drink) and
 * is completely ignorant of which concrete {@link MealBuilder} it's driving — swap in
 * {@link VegMealBuilder} or {@link NonVegMealBuilder} and the same {@link #construct} sequence
 * produces a different {@link Meal}.
 */
public final class Waiter {

    public Meal construct(MealBuilder builder) {
        builder.addMainCourse();
        builder.addSide();
        builder.addDrink();
        return builder.build();
    }
}
