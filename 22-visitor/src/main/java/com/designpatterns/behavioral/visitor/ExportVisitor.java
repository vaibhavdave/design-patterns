package com.designpatterns.behavioral.visitor;

/** A second, unrelated operation (description strings) added with zero changes to any Shape class. */
public final class ExportVisitor implements ShapeVisitor<String> {

    @Override
    public String visitCircle(Circle circle) {
        return "Circle(radius=" + circle.getRadius() + ")";
    }

    @Override
    public String visitRectangle(Rectangle rectangle) {
        return "Rectangle(width=" + rectangle.getWidth() + ", height=" + rectangle.getHeight() + ")";
    }

    @Override
    public String visitTriangle(Triangle triangle) {
        return "Triangle(base=" + triangle.getBase() + ", height=" + triangle.getHeight() + ")";
    }
}
