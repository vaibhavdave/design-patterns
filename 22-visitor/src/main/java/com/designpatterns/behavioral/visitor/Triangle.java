package com.designpatterns.behavioral.visitor;

public final class Triangle implements Shape {

    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public <T> T accept(ShapeVisitor<T> visitor) {
        return visitor.visitTriangle(this);
    }
}
