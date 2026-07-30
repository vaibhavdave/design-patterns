package com.designpatterns.creational.singleton;

/**
 * The idiomatic Java singleton (Effective Java, Item 3). The JVM guarantees an enum constant is
 * instantiated exactly once, even under concurrent class loading, and serialization can't create
 * a second instance the way it can with a plain class. Prefer this form whenever the singleton
 * doesn't need to extend another class.
 */
public enum EnumSingleton {

    INSTANCE;

    private int usageCount;

    public void recordUsage() {
        usageCount++;
    }

    public int getUsageCount() {
        return usageCount;
    }
}
