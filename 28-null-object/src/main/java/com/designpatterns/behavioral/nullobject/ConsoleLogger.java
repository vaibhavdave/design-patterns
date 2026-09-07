package com.designpatterns.behavioral.nullobject;

public class ConsoleLogger implements Logger {

    private final String name;

    public ConsoleLogger(String name) {
        this.name = name;
    }

    @Override
    public void info(String message) {
        System.out.printf("[INFO]  [%s] %s%n", name, message);
    }

    @Override
    public void warn(String message) {
        System.out.printf("[WARN]  [%s] %s%n", name, message);
    }

    @Override
    public void error(String message, Throwable cause) {
        System.out.printf("[ERROR] [%s] %s — %s%n", name, message, cause.toString());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
