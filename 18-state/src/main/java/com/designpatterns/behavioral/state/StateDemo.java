package com.designpatterns.behavioral.state;

/** Drives a full idle -> coin -> select -> dispense -> idle cycle, then exhausts the stock. */
public final class StateDemo {

    public static void main(String[] args) {
        System.out.println("-- Cycle back to Idle (stock remains) --");
        VendingMachineContext machine = new VendingMachineContext(2);
        printState(machine);
        machine.insertCoin();
        printState(machine);
        machine.selectItem();
        printState(machine);
        machine.dispense();
        printState(machine);
        System.out.println("Stock remaining: " + machine.getStock());

        System.out.println();
        System.out.println("-- Out-of-stock path (last item) --");
        VendingMachineContext lastItemMachine = new VendingMachineContext(1);
        lastItemMachine.insertCoin();
        lastItemMachine.selectItem();
        lastItemMachine.dispense();
        printState(lastItemMachine);
        System.out.println("Stock remaining: " + lastItemMachine.getStock());

        try {
            lastItemMachine.insertCoin();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }

    private static void printState(VendingMachineContext machine) {
        System.out.println("Current state: " + machine.getCurrentState().getClass().getSimpleName());
    }
}
