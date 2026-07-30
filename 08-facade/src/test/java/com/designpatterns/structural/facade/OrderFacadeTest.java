package com.designpatterns.structural.facade;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OrderFacadeTest {

    @Test
    void placeOrderSucceedsAndReservesInventoryWhenStockIsSufficient() {
        InventoryService inventoryService = new InventoryService(Map.of("SKU-1", 5));
        OrderFacade facade = new OrderFacade(inventoryService, new PaymentService(), new ShippingService());

        OrderResult result = facade.placeOrder(
                new OrderRequest("ORDER-1", "SKU-1", 2, "CUST-1", 49.98, "1 Main St"));

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getOrderId()).isEqualTo("ORDER-1");
        assertThat(inventoryService.getAvailable("SKU-1")).isEqualTo(3);
    }

    @Test
    void insufficientInventorySurfacesAsACleanFailureResultNotARawException() {
        InventoryService inventoryService = new InventoryService(Map.of("SKU-1", 1));
        OrderFacade facade = new OrderFacade(inventoryService, new PaymentService(), new ShippingService());

        OrderResult result = facade.placeOrder(
                new OrderRequest("ORDER-2", "SKU-1", 10, "CUST-2", 199.90, "2 Main St"));

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).contains("Insufficient inventory");
    }

    @Test
    void insufficientInventoryPreventsPaymentAndShippingFromBeingAttempted() {
        List<String> callLog = new ArrayList<>();
        InventoryService inventoryService = new InventoryService(Map.of("SKU-1", 0));
        PaymentService paymentService = recordingPaymentService(callLog);
        ShippingService shippingService = recordingShippingService(callLog);
        OrderFacade facade = new OrderFacade(inventoryService, paymentService, shippingService);

        facade.placeOrder(new OrderRequest("ORDER-3", "SKU-1", 1, "CUST-3", 9.99, "3 Main St"));

        assertThat(callLog).isEmpty();
    }

    @Test
    void placeOrderCallsSubsystemsInInventoryThenPaymentThenShippingOrder() {
        List<String> callLog = new ArrayList<>();
        InventoryService inventoryService = recordingInventoryService(callLog, Map.of("SKU-1", 5));
        PaymentService paymentService = recordingPaymentService(callLog);
        ShippingService shippingService = recordingShippingService(callLog);
        OrderFacade facade = new OrderFacade(inventoryService, paymentService, shippingService);

        OrderResult result = facade.placeOrder(
                new OrderRequest("ORDER-4", "SKU-1", 1, "CUST-4", 19.99, "4 Main St"));

        assertThat(result.isSuccess()).isTrue();
        assertThat(callLog).containsExactly("inventory", "payment", "shipping");
    }

    @Test
    void paymentFailureIsAlsoTranslatedIntoACleanFailureResult() {
        InventoryService inventoryService = new InventoryService(Map.of("SKU-1", 5));
        OrderFacade facade = new OrderFacade(inventoryService, new PaymentService(), new ShippingService());

        // amount <= 0 is rejected by PaymentService's own validation.
        OrderResult result = facade.placeOrder(
                new OrderRequest("ORDER-5", "SKU-1", 1, "CUST-5", 0.0, "5 Main St"));

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).contains("Charge amount must be positive");
    }

    private static InventoryService recordingInventoryService(List<String> callLog, Map<String, Integer> stock) {
        return new InventoryService(stock) {
            @Override
            public boolean checkAndReserve(String sku, int quantity) {
                callLog.add("inventory");
                return super.checkAndReserve(sku, quantity);
            }
        };
    }

    private static PaymentService recordingPaymentService(List<String> callLog) {
        return new PaymentService() {
            @Override
            public String charge(String customerId, double amount) {
                callLog.add("payment");
                return super.charge(customerId, amount);
            }
        };
    }

    private static ShippingService recordingShippingService(List<String> callLog) {
        return new ShippingService() {
            @Override
            public String scheduleShipment(String orderId, String address) {
                callLog.add("shipping");
                return super.scheduleShipment(orderId, address);
            }
        };
    }
}
