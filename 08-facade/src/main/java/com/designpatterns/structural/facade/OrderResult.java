package com.designpatterns.structural.facade;

/**
 * A clean success/failure outcome the caller can branch on, instead of a raw exception leaking
 * which subsystem failed and why. That translation is part of what the facade is for.
 */
public class OrderResult {

    private final boolean success;
    private final String orderId;
    private final String message;

    private OrderResult(boolean success, String orderId, String message) {
        this.success = success;
        this.orderId = orderId;
        this.message = message;
    }

    public static OrderResult success(String orderId) {
        return new OrderResult(true, orderId, "Order placed successfully");
    }

    public static OrderResult failure(String orderId, String message) {
        return new OrderResult(false, orderId, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }
}
