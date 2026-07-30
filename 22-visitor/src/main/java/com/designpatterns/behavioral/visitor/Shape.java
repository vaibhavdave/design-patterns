package com.designpatterns.behavioral.visitor;

/**
 * {@code accept} is the first half of double dispatch: it dispatches on the shape's own runtime
 * type (each concrete shape calls a different {@code visitXxx} method), and the visitor
 * implementation invoked then dispatches on the operation. Neither dispatch alone would be enough
 * to pick the right code for "this operation, on this shape" with ordinary method overloading.
 */
public interface Shape {

    <T> T accept(ShapeVisitor<T> visitor);
}
