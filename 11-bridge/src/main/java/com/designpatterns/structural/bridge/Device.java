package com.designpatterns.structural.bridge;

/**
 * The implementor hierarchy. Grows independently of {@link RemoteControl} and its subclasses —
 * adding a new device type never requires touching a remote class, and vice versa.
 */
public interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);
}
