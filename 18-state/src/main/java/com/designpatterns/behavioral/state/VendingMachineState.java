package com.designpatterns.behavioral.state;

/**
 * Each concrete state overrides only the transitions that are legal from it; every other action
 * falls through to the {@code default} methods here and fails loudly. That is the point of the
 * pattern — an invalid action in a given state is a compile-time-visible "not overridden" in the
 * concrete class, not one more {@code case} to remember inside a giant switch.
 */
public interface VendingMachineState {

    default void insertCoin(VendingMachineContext context) {
        throw new IllegalStateException("Cannot insert coin while " + describe());
    }

    default void selectItem(VendingMachineContext context) {
        throw new IllegalStateException("Cannot select item while " + describe());
    }

    default void dispense(VendingMachineContext context) {
        throw new IllegalStateException("Cannot dispense while " + describe());
    }

    private String describe() {
        return "in state " + getClass().getSimpleName();
    }
}
