package com.designpatterns.behavioral.strategy;

/** Stand-in for an on-chain transfer. Settlement time is irrelevant to the pattern demo. */
public final class CryptoPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentResult pay(double amount) {
        return PaymentResult.success("Transferred $%.2f in crypto".formatted(amount));
    }
}
