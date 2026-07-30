package com.designpatterns.behavioral.visitor;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ShapeVisitorTest {

    private final AreaCalculatorVisitor areaVisitor = new AreaCalculatorVisitor();
    private final ExportVisitor exportVisitor = new ExportVisitor();

    @Test
    void areaCalculatorComputesCircleArea() {
        Circle circle = new Circle(2.0);

        assertThat(circle.accept(areaVisitor)).isCloseTo(Math.PI * 4.0, within(0.0001));
    }

    @Test
    void areaCalculatorComputesRectangleArea() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);

        assertThat(rectangle.accept(areaVisitor)).isEqualTo(12.0);
    }

    @Test
    void areaCalculatorComputesTriangleArea() {
        Triangle triangle = new Triangle(5.0, 6.0);

        assertThat(triangle.accept(areaVisitor)).isEqualTo(15.0);
    }

    @Test
    void exportVisitorProducesDescriptiveStringPerShapeType() {
        assertThat(new Circle(2.0).accept(exportVisitor)).isEqualTo("Circle(radius=2.0)");
        assertThat(new Rectangle(3.0, 4.0).accept(exportVisitor))
                .isEqualTo("Rectangle(width=3.0, height=4.0)");
        assertThat(new Triangle(5.0, 6.0).accept(exportVisitor))
                .isEqualTo("Triangle(base=5.0, height=6.0)");
    }

    @Test
    void sameShapeListCanBeVisitedByMultipleUnrelatedVisitorsWithoutModification() {
        List<Shape> shapes = List.of(new Circle(1.0), new Rectangle(2.0, 2.0), new Triangle(2.0, 3.0));

        List<Double> areas = shapes.stream().map(shape -> shape.accept(areaVisitor)).toList();
        List<String> exports = shapes.stream().map(shape -> shape.accept(exportVisitor)).toList();

        assertThat(areas).hasSize(3);
        assertThat(exports).hasSize(3);
        assertThat(exports.get(1)).contains("Rectangle");
    }
}
