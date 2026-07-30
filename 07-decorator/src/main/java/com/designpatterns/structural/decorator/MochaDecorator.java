package com.designpatterns.structural.decorator;

public class MochaDecorator extends BeverageDecorator {

    public MochaDecorator(Beverage wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + ", Mocha";
    }

    @Override
    public double getCost() {
        return wrapped.getCost() + 0.45;
    }
}
