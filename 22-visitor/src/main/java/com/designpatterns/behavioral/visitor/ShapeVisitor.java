package com.designpatterns.behavioral.visitor;

/** One method per concrete {@link Shape} type. Adding a new operation means adding a new visitor. */
public interface ShapeVisitor<T> {

    T visitCircle(Circle circle);

    T visitRectangle(Rectangle rectangle);

    T visitTriangle(Triangle triangle);
}
