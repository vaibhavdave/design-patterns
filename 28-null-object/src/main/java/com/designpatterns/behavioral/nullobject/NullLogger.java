package com.designpatterns.behavioral.nullobject;

/**
 * Do-nothing logger. Callers never need to guard with "if (logger != null)".
 * Inject this instead of null when logging is not wanted (tests, silent mode, etc.).
 */
public final class NullLogger implements Logger {

    public static final NullLogger INSTANCE = new NullLogger();

    private NullLogger() {}

    @Override public void info(String message) {}
    @Override public void warn(String message) {}
    @Override public void error(String message, Throwable cause) {}

    @Override
    public boolean isEnabled() {
        return false;
    }
}
