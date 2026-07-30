package com.designpatterns.structural.decorator;

public class WhipDecorator extends BeverageDecorator {

    public WhipDecorator(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + ", Whip";
    }

    @Override
    public double getCost() {
        return wrapped.getCost() + 0.50;
    }
}
