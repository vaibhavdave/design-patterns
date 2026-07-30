package com.designpatterns.behavioral.visitor;

import java.util.List;

/** Runs the same list of shapes through two unrelated visitors without touching any Shape class. */
public final class VisitorDemo {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Circle(2.0),
                new Rectangle(3.0, 4.0),
                new Triangle(5.0, 6.0));

        AreaCalculatorVisitor areaVisitor = new AreaCalculatorVisitor();
        ExportVisitor exportVisitor = new ExportVisitor();

        for (Shape shape : shapes) {
            double area = shape.accept(areaVisitor);
            String export = shape.accept(exportVisitor);
            System.out.printf("%-40s area=%.2f%n", export, area);
        }
    }
}
