package com.designpatterns.behavioral.visitor;

/** One operation (area), implemented for every shape, without a single method on {@link Shape}. */
public final class AreaCalculatorVisitor implements ShapeVisitor<Double> {

    @Override
    public Double visitCircle(Circle circle) {
        return Math.PI * circle.getRadius() * circle.getRadius();
    }

    @Override
    public Double visitRectangle(Rectangle rectangle) {
        return rectangle.getWidth() * rectangle.getHeight();
    }

    @Override
    public Double visitTriangle(Triangle triangle) {
        return 0.5 * triangle.getBase() * triangle.getHeight();
    }
}
