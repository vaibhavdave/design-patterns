package com.designpatterns.behavioral.command;

/** Undo of a speed increase is a speed decrease — a reversible step, not a full state snapshot. */
public final class FanSpeedUpCommand implements Command {

    private final Fan fan;

    public FanSpeedUpCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.speedUp();
    }

    @Override
    public void undo() {
        fan.speedDown();
    }
}
