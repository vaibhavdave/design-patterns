package com.designpatterns.structural.facade;

/**
 * The single entry point a client needs. {@link #placeOrder(OrderRequest)} hides the three
 * subsystem calls, the order they must happen in (reserve stock before charging a card; charge
 * before scheduling a shipment nobody's paid for), and translates any subsystem failure into a
 * clean {@link OrderResult} instead of letting a subsystem-specific exception escape.
 */
public class OrderFacade {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;

    public OrderFacade(InventoryService inventoryService, PaymentService paymentService,
                        ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
    }

    public OrderResult placeOrder(OrderRequest request) {
        try {
            boolean reserved = inventoryService.checkAndReserve(request.getSku(), request.getQuantity());
            if (!reserved) {
                return OrderResult.failure(request.getOrderId(),
                        "Insufficient inventory for sku " + request.getSku());
            }

            paymentService.charge(request.getCustomerId(), request.getAmount());
            shippingService.scheduleShipment(request.getOrderId(), request.getShippingAddress());

            return OrderResult.success(request.getOrderId());
        } catch (InventoryException | PaymentException | ShippingException e) {
            return OrderResult.failure(request.getOrderId(), e.getMessage());
        }
    }
}
