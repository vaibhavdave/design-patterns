package com.designpatterns.structural.decorator;

public class MilkDecorator extends BeverageDecorator {

    public MilkDecorator(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return wrapped.getCost() + 0.35;
    }
}
