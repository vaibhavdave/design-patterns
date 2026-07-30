package com.designpatterns.behavioral.command;

/** Runnable walkthrough: {@code ./gradlew :16-command:run} (or run this class from your IDE). */
public final class RemoteControlDemo {

    private RemoteControlDemo() {
    }

    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Fan bedroomFan = new Fan("Bedroom");
        RemoteControlInvoker remote = new RemoteControlInvoker();

        System.out.println("-- Executing a sequence of commands --");
        remote.pressButton(new LightOnCommand(livingRoomLight));
        System.out.println(livingRoomLight.getName() + " on: " + livingRoomLight.isOn());

        remote.pressButton(new FanSpeedUpCommand(bedroomFan));
        remote.pressButton(new FanSpeedUpCommand(bedroomFan));
        System.out.println(bedroomFan.getName() + " speed: " + bedroomFan.getSpeed());

        System.out.println();
        System.out.println("-- Undoing in reverse order --");
        remote.pressUndo();
        System.out.println(bedroomFan.getName() + " speed after undo: " + bedroomFan.getSpeed());

        remote.pressUndo();
        System.out.println(bedroomFan.getName() + " speed after undo: " + bedroomFan.getSpeed());

        remote.pressUndo();
        System.out.println(livingRoomLight.getName() + " on after undo: " + livingRoomLight.isOn());

        System.out.println();
        System.out.println("-- Undo with empty history is a no-op --");
        remote.pressUndo();
        System.out.println("No exception thrown; history size = " + remote.historySize());
    }
}
