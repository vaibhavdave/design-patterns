package com.designpatterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Deep-copies both nested reference fields it owns: {@link Point} (via its copy constructor) and
 * the {@code tags} list (via {@code new ArrayList<>(tags)}). Skipping either would reproduce the
 * same shallow-copy bug {@link BuggyShallowCircle} demonstrates, just on a different field.
 */
public final class Rectangle implements Shape {

    private final Point position;
    private final double width;
    private final double height;
    private final List<String> tags;

    public Rectangle(Point position, double width, double height, List<String> tags) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.tags = new ArrayList<>(tags);
    }

    public Point position() {
        return position;
    }

    public double width() {
        return width;
    }

    public double height() {
        return height;
    }

    public List<String> tags() {
        return tags;
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(new Point(position), width, height, new ArrayList<>(tags));
    }

    @Override
    public String describe() {
        return "Rectangle{position=" + position + ", width=" + width + ", height=" + height
                + ", tags=" + tags + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rectangle other)) {
            return false;
        }
        return Double.compare(width, other.width) == 0
                && Double.compare(height, other.height) == 0
                && Double.compare(position.x(), other.position.x()) == 0
                && Double.compare(position.y(), other.position.y()) == 0
                && tags.equals(other.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position.x(), position.y(), width, height, tags);
    }
}
