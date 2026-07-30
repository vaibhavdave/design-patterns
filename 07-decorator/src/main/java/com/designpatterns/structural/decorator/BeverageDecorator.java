package com.designpatterns.structural.decorator;

/**
 * Wraps a {@link Beverage} and implements the same interface, so decorators are transparent to
 * whatever holds a {@code Beverage} reference — and can themselves be wrapped by further
 * decorators. Concrete decorators only need to implement how they extend cost/description; the
 * wrapping mechanics live once here.
 */
public abstract class BeverageDecorator implements Beverage {

    protected final Beverage wrapped;

    protected BeverageDecorator(Beverage wrapped) {
        this.wrapped = wrapped;
    }
}
