package com.designpatterns.behavioral.command;

/** A Receiver with a bounded speed level instead of a simple on/off flag. */
public final class Fan {

    private static final int MAX_SPEED = 3;

    private final String name;
    private int speed;

    public Fan(String name) {
        this.name = name;
    }

    public void speedUp() {
        if (speed < MAX_SPEED) {
            speed++;
        }
    }

    public void speedDown() {
        if (speed > 0) {
            speed--;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }
}
