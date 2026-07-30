package com.designpatterns.creational.builder;

import java.util.ArrayList;
import java.util.List;

public final class VegMealBuilder implements MealBuilder {

    private final List<String> items = new ArrayList<>();

    @Override
    public void addMainCourse() {
        items.add("Paneer Tikka");
    }

    @Override
    public void addSide() {
        items.add("Dal Makhani");
    }

    @Override
    public void addDrink() {
        items.add("Lassi");
    }

    @Override
    public Meal build() {
        return new Meal(items);
    }
}
