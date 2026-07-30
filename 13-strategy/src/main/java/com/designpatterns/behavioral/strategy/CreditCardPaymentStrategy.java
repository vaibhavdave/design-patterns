package com.designpatterns.behavioral.strategy;

/** Stand-in for a card-network charge. No real network call — this module is about the pattern. */
public final class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentResult pay(double amount) {
        return PaymentResult.success("Charged $%.2f to credit card".formatted(amount));
    }
}
