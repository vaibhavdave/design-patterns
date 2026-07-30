package com.designpatterns.behavioral.state;

/**
 * The machine is actively releasing an item. Coins and item selections are rejected while this is
 * in progress (the default methods on {@link VendingMachineState} handle that). Once
 * {@link #dispense} runs, the state itself decides whether the machine has anything left to sell.
 */
public final class DispensingState implements VendingMachineState {

    @Override
    public void dispense(VendingMachineContext context) {
        context.decrementStock();
        if (context.getStock() > 0) {
            context.setState(new IdleState());
        } else {
            context.setState(new OutOfStockState());
        }
    }
}
