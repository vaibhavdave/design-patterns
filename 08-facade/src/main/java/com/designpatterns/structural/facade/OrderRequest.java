package com.designpatterns.structural.facade;

public class OrderRequest {

    private final String orderId;
    private final String sku;
    private final int quantity;
    private final String customerId;
    private final double amount;
    private final String shippingAddress;

    public OrderRequest(String orderId, String sku, int quantity, String customerId, double amount,
                         String shippingAddress) {
        this.orderId = orderId;
        this.sku = sku;
        this.quantity = quantity;
        this.customerId = customerId;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}
