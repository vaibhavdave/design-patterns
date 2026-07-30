package com.designpatterns.structural.bridge;

/**
 * The abstraction. Holds a {@link Device} by composition, NOT by extending any concrete device —
 * that composed reference is the "bridge" between the remote hierarchy and the device hierarchy.
 * Any {@code RemoteControl} subclass works with any {@link Device} implementation without either
 * side knowing about the other's concrete types.
 */
public class RemoteControl {

    protected final Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }
}
