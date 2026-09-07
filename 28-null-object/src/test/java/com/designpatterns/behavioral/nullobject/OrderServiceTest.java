package com.designpatterns.behavioral.nullobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceTest {

    // OrderService never does null-checks on its logger; NullLogger absorbs all calls silently.
    private final OrderService service = new OrderService(NullLogger.INSTANCE);

    @Test
    void placeOrderReturnsOrderId() {
        String orderId = service.placeOrder("Widget", 2);
        assertThat(orderId).startsWith("ORD-");
    }

    @Test
    void placeOrderRejectsNonPositiveQuantity() {
        assertThat(service.placeOrder("Widget", 0)).isEqualTo("REJECTED");
        assertThat(service.placeOrder("Widget", -5)).isEqualTo("REJECTED");
    }

    @Test
    void cancelOrderThrowsForBlankId() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> service.cancelOrder(""))
                .withMessage("orderId must not be blank");
    }

    @Test
    void cancelOrderSucceedsForValidId() {
        String orderId = service.placeOrder("Gadget", 1);
        assertThatNoException().isThrownBy(() -> service.cancelOrder(orderId));
    }

}
