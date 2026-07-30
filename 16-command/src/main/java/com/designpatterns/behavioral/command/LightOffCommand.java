package com.designpatterns.behavioral.command;

/** The mirror image of {@link LightOnCommand}: undo turns the light back on. */
public final class LightOffCommand implements Command {

    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}
