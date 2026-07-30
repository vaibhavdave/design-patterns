package com.designpatterns.creational.singleton;

/**
 * The textbook-naive singleton. Lazily creates the instance on first call.
 *
 * <p>This is intentionally broken under concurrency: two threads can both pass the
 * {@code if (instance == null)} check before either has finished assigning {@code instance},
 * producing two separate objects. Kept here so {@link SingletonThreadSafetyTest} can demonstrate
 * the failure before the thread-safe variants fix it.
 */
public final class NaiveSingleton {

    private static NaiveSingleton instance;

    private NaiveSingleton() {
    }

    public static NaiveSingleton getInstance() {
        if (instance == null) {
            instance = new NaiveSingleton();
        }
        return instance;
    }
}
