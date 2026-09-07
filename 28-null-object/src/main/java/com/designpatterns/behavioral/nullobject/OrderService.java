package com.designpatterns.behavioral.nullobject;

/**
 * Business service that uses a Logger.
 * It never checks "if (logger != null)" — the Null Object guarantees that contract.
 */
public class OrderService {

    private final Logger logger;

    public OrderService(Logger logger) {
        this.logger = logger;
    }

    public String placeOrder(String item, int quantity) {
        if (quantity <= 0) {
            logger.warn("Attempted to place order with non-positive quantity: " + quantity);
            return "REJECTED";
        }
        String orderId = "ORD-" + System.nanoTime();
        logger.info("Placed order " + orderId + " — " + quantity + "x " + item);
        return orderId;
    }

    public void cancelOrder(String orderId) {
        if (orderId == null || orderId.isBlank()) {
            logger.warn("cancelOrder called with invalid orderId");
            throw new IllegalArgumentException("orderId must not be blank");
        }
        try {
            logger.info("Cancelling order " + orderId);
            logger.info("Order " + orderId + " cancelled successfully");
        } catch (Exception e) {
            logger.error("Failed to cancel order " + orderId, e);
            throw e;
        }
    }
}
