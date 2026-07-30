package com.designpatterns.creational.prototype;

/**
 * The correct, deep-copying prototype for a circle. {@link #copy()} builds a *new* {@link Point}
 * via {@link Point#Point(Point)} rather than handing out the same reference — contrast with
 * {@link BuggyShallowCircle}, which is kept in this module specifically to demonstrate the bug
 * this class avoids.
 */
public final class Circle implements Shape {

    private final Point center;
    private final double radius;
    private final String color;

    public Circle(Point center, double radius, String color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Point center() {
        return center;
    }

    public double radius() {
        return radius;
    }

    public String color() {
        return color;
    }

    @Override
    public Circle copy() {
        return new Circle(new Point(center), radius, color);
    }

    @Override
    public String describe() {
        return "Circle{center=" + center + ", radius=" + radius + ", color=" + color + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Circle other)) {
            return false;
        }
        return Double.compare(radius, other.radius) == 0
                && Double.compare(center.x(), other.center.x()) == 0
                && Double.compare(center.y(), other.center.y()) == 0
                && color.equals(other.color);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(center.x(), center.y(), radius, color);
    }
}
