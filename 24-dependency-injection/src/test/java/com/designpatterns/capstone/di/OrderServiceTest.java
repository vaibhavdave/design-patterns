package com.designpatterns.capstone.di;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderServiceTest {

    @Test
    void chargesThroughWhicheverGatewayIsInjected() {
        PaymentGateway.PaymentResult stubbed = new PaymentGateway.PaymentResult(true, "stub-1");
        OrderService orderService = new OrderService(amount -> stubbed);

        assertThat(orderService.placeOrder(10.0)).isSameAs(stubbed);
    }

    @Test
    void rejectsNonPositiveAmountsBeforeTouchingTheGateway() {
        OrderService orderService = new OrderService(amount -> {
            throw new AssertionError("gateway should never be called for an invalid amount");
        });

        assertThatThrownBy(() -> orderService.placeOrder(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
