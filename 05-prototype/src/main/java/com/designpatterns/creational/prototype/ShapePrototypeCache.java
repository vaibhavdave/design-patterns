package com.designpatterns.creational.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The actual point of Prototype: callers ask for a copy of a pre-configured instance by key
 * instead of constructing (and re-initializing) a shape from scratch, and they never need to
 * import {@link Circle} or {@link Rectangle} directly — only {@link Shape}. If constructing a
 * shape were expensive (parsed from a file, computed from a mesh, whatever), this is where that
 * cost gets paid exactly once, at registration time.
 */
public final class ShapePrototypeCache {

    private final Map<String, Shape> prototypes = new HashMap<>();

    public static ShapePrototypeCache withDefaults() {
        ShapePrototypeCache cache = new ShapePrototypeCache();
        cache.register("default-circle", new Circle(new Point(0, 0), 1.0, "black"));
        cache.register("default-rectangle",
                new Rectangle(new Point(0, 0), 2.0, 1.0, List.of("shape", "default")));
        return cache;
    }

    public void register(String key, Shape prototype) {
        prototypes.put(key, prototype);
    }

    /** Returns an independent copy of the prototype registered under {@code key}. */
    public Shape get(String key) {
        Shape prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype registered for key: " + key);
        }
        return prototype.copy();
    }
}
