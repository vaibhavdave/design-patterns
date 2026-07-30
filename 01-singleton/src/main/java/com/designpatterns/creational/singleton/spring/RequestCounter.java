package com.designpatterns.creational.singleton.spring;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A plain object with no singleton logic of its own. Whether callers see one shared instance or
 * a fresh one every time is entirely decided by the {@code @Scope} on the bean definition in
 * {@link SingletonSpringConfig} — that's the point of the demo: Spring, not the class, owns
 * lifecycle.
 */
public class RequestCounter {

    private final AtomicInteger count = new AtomicInteger();

    public int increment() {
        return count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
