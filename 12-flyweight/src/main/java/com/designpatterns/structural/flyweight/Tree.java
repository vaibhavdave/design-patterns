package com.designpatterns.structural.flyweight;

/**
 * Holds only the extrinsic (per-instance) state — its position — plus a reference to the shared
 * {@link TreeType}. Thousands of {@code Tree} instances can point at a handful of {@code
 * TreeType} instances.
 */
public class Tree {

    private final int x;
    private final int y;
    private final TreeType type;

    Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TreeType getType() {
        return type;
    }

    public void draw() {
        type.draw(x, y);
    }
}
