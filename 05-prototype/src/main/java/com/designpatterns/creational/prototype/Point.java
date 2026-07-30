package com.designpatterns.creational.prototype;

/**
 * Deliberately mutable, with public setters. This is the nested object every {@link Shape} holds
 * a reference to, and mutability is exactly what makes shallow-vs-deep copying observable: if a
 * copy shares this object with its original, moving the copy silently moves the original too.
 */
public final class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Copy constructor — used by every correct deep-copy {@link Shape#copy()} implementation. */
    public Point(Point other) {
        this(other.x, other.y);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
