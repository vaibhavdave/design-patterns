package com.designpatterns.behavioral.command;

/** Wraps a {@link Light} receiver; undo reverses execute by turning it back off. */
public final class LightOnCommand implements Command {

    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
