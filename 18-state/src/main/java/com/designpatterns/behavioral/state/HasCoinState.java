package com.designpatterns.behavioral.state;

/** A coin has been accepted. The only legal action is selecting an item, which starts dispensing. */
public final class HasCoinState implements VendingMachineState {

    @Override
    public void selectItem(VendingMachineContext context) {
        context.setState(new DispensingState());
    }
}
