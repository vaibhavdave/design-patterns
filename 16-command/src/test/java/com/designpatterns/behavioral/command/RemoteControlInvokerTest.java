package com.designpatterns.behavioral.command;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class RemoteControlInvokerTest {

    @Test
    void pressButtonExecutesTheCommandImmediately() {
        Light light = new Light("Kitchen");
        RemoteControlInvoker remote = new RemoteControlInvoker();

        remote.pressButton(new LightOnCommand(light));

        assertThat(light.isOn()).isTrue();
    }

    @Test
    void undoReversesTheMostRecentlyExecutedCommand() {
        Light light = new Light("Kitchen");
        RemoteControlInvoker remote = new RemoteControlInvoker();
        remote.pressButton(new LightOnCommand(light));

        remote.pressUndo();

        assertThat(light.isOn()).isFalse();
    }

    @Test
    void undoingASequenceRestoresStateInReverseOrder() {
        Fan fan = new Fan("Bedroom");
        RemoteControlInvoker remote = new RemoteControlInvoker();

        remote.pressButton(new FanSpeedUpCommand(fan));
        remote.pressButton(new FanSpeedUpCommand(fan));
        remote.pressButton(new FanSpeedUpCommand(fan));
        assertThat(fan.getSpeed()).isEqualTo(3);

        remote.pressUndo();
        assertThat(fan.getSpeed()).isEqualTo(2);

        remote.pressUndo();
        assertThat(fan.getSpeed()).isEqualTo(1);

        remote.pressUndo();
        assertThat(fan.getSpeed()).isEqualTo(0);
    }

    @Test
    void undoWithEmptyHistoryIsAGracefulNoOp() {
        RemoteControlInvoker remote = new RemoteControlInvoker();

        assertThatCode(remote::pressUndo).doesNotThrowAnyException();
        assertThat(remote.historySize()).isZero();
    }

    @Test
    void historySizeTracksExecutedButNotYetUndoneCommands() {
        Light light = new Light("Hallway");
        RemoteControlInvoker remote = new RemoteControlInvoker();

        remote.pressButton(new LightOnCommand(light));
        remote.pressButton(new LightOffCommand(light));
        assertThat(remote.historySize()).isEqualTo(2);

        remote.pressUndo();
        assertThat(remote.historySize()).isEqualTo(1);
    }
}
