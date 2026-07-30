package com.designpatterns.structural.facade;

import java.util.UUID;

public class ShippingService {

    public String scheduleShipment(String orderId, String address) {
        validateRequest(orderId, address);
        // Simulated dispatch — a real implementation would talk to a carrier API here.
        return "trk-" + UUID.randomUUID();
    }

    private void validateRequest(String orderId, String address) {
        if (orderId == null || orderId.isBlank()) {
            throw new ShippingException("Order id must not be blank");
        }
        if (address == null || address.isBlank()) {
            throw new ShippingException("Shipping address must not be blank");
        }
    }
}
