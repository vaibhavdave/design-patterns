package com.designpatterns.structural.facade;

import java.util.UUID;

public class PaymentService {

    public String charge(String customerId, double amount) {
        validateRequest(customerId, amount);
        // Simulated charge — a real implementation would call out to a payment gateway here.
        return "txn-" + UUID.randomUUID();
    }

    private void validateRequest(String customerId, double amount) {
        if (customerId == null || customerId.isBlank()) {
            throw new PaymentException("Customer id must not be blank");
        }
        if (amount <= 0) {
            throw new PaymentException("Charge amount must be positive");
        }
    }
}
