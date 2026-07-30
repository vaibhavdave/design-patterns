package com.designpatterns.structural.flyweight;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * The flyweight factory. Guarantees that requesting the same (name, color, texture) combination
 * twice returns the exact same {@link TreeType} instance instead of constructing a new one, which
 * is what makes sharing actually happen.
 */
public class TreeFactory {

    private final Map<String, TreeType> cache = new ConcurrentHashMap<>();

    public TreeType getTreeType(String name, String color, String texture) {
        String key = name + "|" + color + "|" + texture;
        return cache.computeIfAbsent(key, k -> new TreeType(name, color, texture));
    }

    public int getCreatedTypeCount() {
        return cache.size();
    }
}
