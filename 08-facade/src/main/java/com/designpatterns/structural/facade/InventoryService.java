package com.designpatterns.structural.facade;

import java.util.HashMap;
import java.util.Map;

/**
 * One of the three subsystems the facade coordinates. Each public method quietly does its own
 * validation before the "real" work — exactly the kind of step-ordering detail a facade exists to
 * hide from callers.
 */
public class InventoryService {

    private final Map<String, Integer> stockBySku = new HashMap<>();

    public InventoryService(Map<String, Integer> initialStock) {
        this.stockBySku.putAll(initialStock);
    }

    public boolean checkAndReserve(String sku, int quantity) {
        validateRequest(sku, quantity);
        Integer available = stockBySku.get(sku);
        if (available == null || available < quantity) {
            return false;
        }
        stockBySku.put(sku, available - quantity);
        return true;
    }

    public int getAvailable(String sku) {
        return stockBySku.getOrDefault(sku, 0);
    }

    private void validateRequest(String sku, int quantity) {
        if (sku == null || sku.isBlank()) {
            throw new InventoryException("SKU must not be blank");
        }
        if (quantity <= 0) {
            throw new InventoryException("Quantity must be positive");
        }
    }
}
