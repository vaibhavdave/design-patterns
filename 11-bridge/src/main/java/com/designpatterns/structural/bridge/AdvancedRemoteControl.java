package com.designpatterns.structural.bridge;

/**
 * A refinement of the abstraction that adds {@link #mute()}. Note it still only ever talks to
 * {@link #device} through the {@link Device} interface — it works unmodified with a {@link Tv},
 * a {@link Radio}, or any future {@code Device} implementation.
 */
public class AdvancedRemoteControl extends RemoteControl {

    private int volumeBeforeMute = -1;

    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        if (volumeBeforeMute == -1) {
            volumeBeforeMute = device.getVolume();
            device.setVolume(0);
        } else {
            device.setVolume(volumeBeforeMute);
            volumeBeforeMute = -1;
        }
    }
}
