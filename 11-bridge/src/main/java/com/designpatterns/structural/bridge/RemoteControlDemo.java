package com.designpatterns.structural.bridge;

public final class RemoteControlDemo {

    private RemoteControlDemo() {
    }

    public static void main(String[] args) {
        // The SAME AdvancedRemoteControl class, working with two entirely different Device
        // implementations — that's the two hierarchies varying independently.
        AdvancedRemoteControl tvRemote = new AdvancedRemoteControl(new Tv());
        runDemo("TV", tvRemote);

        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(new Radio());
        runDemo("Radio", radioRemote);
    }

    private static void runDemo(String label, AdvancedRemoteControl remote) {
        System.out.println("--- " + label + " ---");
        remote.togglePower();
        remote.volumeUp();
        remote.volumeUp();
        remote.mute();
        remote.mute();
        remote.togglePower();
    }
}
