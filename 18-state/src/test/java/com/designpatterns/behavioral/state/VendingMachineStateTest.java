package com.designpatterns.behavioral.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VendingMachineStateTest {

    @Test
    void startsInIdleStateWhenStockIsAvailable() {
        VendingMachineContext machine = new VendingMachineContext(3);

        assertThat(machine.getCurrentState()).isInstanceOf(IdleState.class);
    }

    @Test
    void startsInOutOfStockStateWhenNoStockIsAvailable() {
        VendingMachineContext machine = new VendingMachineContext(0);

        assertThat(machine.getCurrentState()).isInstanceOf(OutOfStockState.class);
    }

    @Test
    void fullCycleReturnsToIdleWhenStockRemains() {
        VendingMachineContext machine = new VendingMachineContext(2);

        machine.insertCoin();
        assertThat(machine.getCurrentState()).isInstanceOf(HasCoinState.class);

        machine.selectItem();
        assertThat(machine.getCurrentState()).isInstanceOf(DispensingState.class);

        machine.dispense();
        assertThat(machine.getCurrentState()).isInstanceOf(IdleState.class);
        assertThat(machine.getStock()).isEqualTo(1);
    }

    @Test
    void dispensingLastItemTransitionsToOutOfStock() {
        VendingMachineContext machine = new VendingMachineContext(1);

        machine.insertCoin();
        machine.selectItem();
        machine.dispense();

        assertThat(machine.getCurrentState()).isInstanceOf(OutOfStockState.class);
        assertThat(machine.getStock()).isZero();
    }

    @Test
    void selectingItemWithoutInsertingCoinIsRejected() {
        VendingMachineContext machine = new VendingMachineContext(2);

        assertThatThrownBy(machine::selectItem).isInstanceOf(IllegalStateException.class);
        assertThat(machine.getCurrentState()).isInstanceOf(IdleState.class);
    }

    @Test
    void insertingCoinWhenOutOfStockIsRejected() {
        VendingMachineContext machine = new VendingMachineContext(0);

        assertThatThrownBy(machine::insertCoin).isInstanceOf(IllegalStateException.class);
    }
}
