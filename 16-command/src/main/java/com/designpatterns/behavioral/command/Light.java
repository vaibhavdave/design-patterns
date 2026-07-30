package com.designpatterns.behavioral.command;

/** A Receiver: knows how to actually turn itself on/off, with no idea any Command exists. */
public final class Light {

    private final String name;
    private boolean on;

    public Light(String name) {
        this.name = name;
    }

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public boolean isOn() {
        return on;
    }

    public String getName() {
        return name;
    }
}
