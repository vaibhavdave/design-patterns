package com.designpatterns.behavioral.state;

/** Waiting for money. The only legal action is inserting a coin. */
public final class IdleState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachineContext context) {
        context.setState(new HasCoinState());
    }
}
