package com.designpatterns.creational.singleton;

/**
 * Fixes {@link NaiveSingleton}'s race by synchronizing the whole accessor. Correct, but every
 * call pays the lock's cost even after the instance already exists — the motivation for
 * {@link DoubleCheckedLockingSingleton}.
 */
public final class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
