package com.designpatterns.structural.decorator;

/**
 * The component interface shared by base drinks and every decorator, so decorators can wrap
 * either another decorator or a plain base component interchangeably.
 */
public interface Beverage {

    String getDescription();

    double getCost();
}
