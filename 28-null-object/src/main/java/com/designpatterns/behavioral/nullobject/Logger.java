package com.designpatterns.behavioral.nullobject;

public interface Logger {
    void info(String message);
    void warn(String message);
    void error(String message, Throwable cause);
    boolean isEnabled();
}
