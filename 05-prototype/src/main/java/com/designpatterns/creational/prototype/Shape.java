package com.designpatterns.creational.prototype;

/**
 * The Prototype role. Deliberately its own method, not {@link Object#clone()}: {@code clone()} is
 * a well-documented Java footgun — {@code Cloneable} carries no methods of its own, the contract
 * relies on a magical protected native method, arrays are the only type it works well for out of
 * the box, and every class in a hierarchy has to opt in correctly or subclasses silently break.
 * A plain {@code copy()} method is ordinary polymorphism: no marker interface, no checked
 * {@code CloneNotSupportedException}, and the return type can be covariant.
 */
public interface Shape {

    /** Returns an independent copy: mutating the result must never affect {@code this}. */
    Shape copy();

    String describe();
}
