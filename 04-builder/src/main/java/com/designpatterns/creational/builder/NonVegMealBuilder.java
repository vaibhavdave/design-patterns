package com.designpatterns.creational.builder;

import java.util.ArrayList;
import java.util.List;

public final class NonVegMealBuilder implements MealBuilder {

    private final List<String> items = new ArrayList<>();

    @Override
    public void addMainCourse() {
        items.add("Butter Chicken");
    }

    @Override
    public void addSide() {
        items.add("Naan");
    }

    @Override
    public void addDrink() {
        items.add("Soda");
    }

    @Override
    public Meal build() {
        return new Meal(items);
    }
}
