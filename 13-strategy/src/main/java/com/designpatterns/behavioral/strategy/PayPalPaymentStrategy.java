package com.designpatterns.behavioral.strategy;

/** Stand-in for a PayPal redirect-and-capture flow. */
public final class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentResult pay(double amount) {
        return PaymentResult.success("Paid $%.2f via PayPal".formatted(amount));
    }
}
