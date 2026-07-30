package com.designpatterns.structural.bridge;

public class Tv implements Device {

    private boolean enabled;
    private int volume = 20;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        volume = clamp(percent);
    }

    private static int clamp(int percent) {
        return Math.max(0, Math.min(100, percent));
    }
}
