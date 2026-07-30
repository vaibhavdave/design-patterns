package com.designpatterns.creational.builder;

import java.util.Collections;
import java.util.List;

/** The product the classic GoF builder trio ({@link MealBuilder}, its concretes, {@link Waiter}) assembles. */
public final class Meal {

    private final List<String> items;

    Meal(List<String> items) {
        this.items = Collections.unmodifiableList(items);
    }

    public List<String> items() {
        return items;
    }

    @Override
    public String toString() {
        return String.join(" + ", items);
    }
}
