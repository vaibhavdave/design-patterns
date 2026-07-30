package com.designpatterns.behavioral.strategy;

/**
 * The interchangeable algorithm. {@link Checkout} depends only on this interface, never on a
 * concrete strategy, so new payment methods can be added without touching {@code Checkout} at
 * all — the Open/Closed half of the pattern's payoff.
 */
public interface PaymentStrategy {

    PaymentResult pay(double amount);
}
