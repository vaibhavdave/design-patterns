package com.designpatterns.capstone.di;

/**
 * Depends on the {@link PaymentGateway} abstraction only, injected through the constructor. This
 * class never decides which gateway to use — that decision is pushed out to whoever constructs
 * it (see {@link ManualDIDemo} for a hand-wired example and {@code spring/DiConfig} for a
 * container-wired one). That's the whole pattern: inversion of the "who chooses my dependency"
 * control.
 */
public class OrderService {

    private final PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentGateway.PaymentResult placeOrder(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Order amount must be positive");
        }
        return paymentGateway.charge(amount);
    }
}
