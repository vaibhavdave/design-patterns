package com.designpatterns.behavioral.strategy;

/**
 * Outcome of a payment attempt. A simple immutable record is enough here — the point of this
 * module is which algorithm ran, not how rich the result type is.
 */
public record PaymentResult(boolean success, String message) {

    public static PaymentResult success(String message) {
        return new PaymentResult(true, message);
    }
}
