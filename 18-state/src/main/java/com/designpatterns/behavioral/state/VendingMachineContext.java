package com.designpatterns.behavioral.state;

/**
 * The context never contains an {@code if}/{@code switch} on "what state am I in" — it just holds
 * a reference to the current {@link VendingMachineState} and forwards every call to it. States
 * transition the machine themselves by calling {@link #setState(VendingMachineState)}, so the
 * context stays a thin, unchanging dispatcher no matter how many states get added later.
 */
public final class VendingMachineContext {

    private VendingMachineState currentState;
    private int stock;

    public VendingMachineContext(int initialStock) {
        this.stock = initialStock;
        this.currentState = initialStock > 0 ? new IdleState() : new OutOfStockState();
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public int getStock() {
        return stock;
    }

    void decrementStock() {
        stock--;
    }

    public void insertCoin() {
        currentState.insertCoin(this);
    }

    public void selectItem() {
        currentState.selectItem(this);
    }

    public void dispense() {
        currentState.dispense(this);
    }
}
