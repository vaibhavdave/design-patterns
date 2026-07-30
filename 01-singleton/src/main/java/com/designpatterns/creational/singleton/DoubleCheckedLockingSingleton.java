package com.designpatterns.creational.singleton;

/**
 * Only synchronizes on the first call, when {@code instance} is still null; every later call
 * reads the {@code volatile} field without ever entering the {@code synchronized} block. The
 * {@code volatile} modifier is required — without it a thread could observe a partially
 * constructed object due to instruction reordering during {@code new ThreadSafeSingleton()}.
 */
public final class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        DoubleCheckedLockingSingleton result = instance;
        if (result == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                result = instance;
                if (result == null) {
                    instance = result = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return result;
    }
}
