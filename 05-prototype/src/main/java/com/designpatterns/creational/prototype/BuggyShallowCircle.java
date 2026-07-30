package com.designpatterns.creational.prototype;

/**
 * Intentionally broken. This is the copy() every developer writes first: reuse the existing
 * {@link Point} reference instead of copying it. It compiles, it passes a naive "is the radius
 * right?" test, and it silently lets a mutation on the copy leak back into the original — see
 * {@code PrototypeCopyTest#shallowCopyBugMutatingTheCloneAlsoMovesTheOriginal}. Kept here purely
 * as a teaching contrast with {@link Circle#copy()}; the {@link ShapePrototypeCache} never
 * registers this class.
 */
public final class BuggyShallowCircle implements Shape {

    private final Point center;
    private final double radius;

    public BuggyShallowCircle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point center() {
        return center;
    }

    @Override
    public BuggyShallowCircle copy() {
        // BUG: hands out the SAME Point instance instead of new Point(center).
        return new BuggyShallowCircle(center, radius);
    }

    @Override
    public String describe() {
        return "BuggyShallowCircle{center=" + center + ", radius=" + radius + "}";
    }
}
