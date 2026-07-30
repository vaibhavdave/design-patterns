package com.designpatterns.behavioral.strategy;

/**
 * The Context. It holds a reference to a {@link PaymentStrategy} and delegates {@code pay} to
 * whichever one is currently plugged in — the strategy can be swapped on the SAME {@code
 * Checkout} instance via {@link #setPaymentStrategy}, proving the algorithm varies independently
 * of the object using it.
 */
public final class Checkout {

    private PaymentStrategy paymentStrategy;

    public Checkout(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentResult completePurchase(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("No payment strategy configured");
        }
        return paymentStrategy.pay(amount);
    }
}
