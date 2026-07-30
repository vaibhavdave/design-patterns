package com.designpatterns.structural.facade;

import java.util.Map;

public final class OrderFacadeDemo {

    private OrderFacadeDemo() {
    }

    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService(Map.of("SKU-1", 5));
        PaymentService paymentService = new PaymentService();
        ShippingService shippingService = new ShippingService();
        OrderFacade orderFacade = new OrderFacade(inventoryService, paymentService, shippingService);

        // The client makes exactly one call. It never sees InventoryService, PaymentService, or
        // ShippingService, and never has to decide what order to call them in.
        OrderResult ok = orderFacade.placeOrder(
                new OrderRequest("ORDER-1", "SKU-1", 2, "CUST-1", 49.98, "1 Main St"));
        System.out.println("Order 1 -> success=" + ok.isSuccess() + " message=" + ok.getMessage());

        OrderResult failed = orderFacade.placeOrder(
                new OrderRequest("ORDER-2", "SKU-1", 10, "CUST-2", 199.90, "2 Main St"));
        System.out.println("Order 2 -> success=" + failed.isSuccess() + " message=" + failed.getMessage());
    }
}
