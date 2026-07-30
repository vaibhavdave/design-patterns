package com.designpatterns.capstone.di.spring;

import com.designpatterns.capstone.di.OrderService;
import com.designpatterns.capstone.di.PaymentGateway;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class DiConfigTest {

    @Test
    void containerInjectsOrderServiceWithTheConfiguredGateway() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(DiConfig.class)) {
            OrderService orderService = context.getBean(OrderService.class);

            PaymentGateway.PaymentResult result = orderService.placeOrder(25.0);

            assertThat(result.success()).isTrue();
            assertThat(result.reference()).startsWith("stripe-");
        }
    }

    @Test
    void auditLoggerLifecycleCallbacksRunAroundContextStartAndClose() {
        AuditLogger auditLogger;
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(DiConfig.class)) {
            auditLogger = context.getBean(AuditLogger.class);
            assertThat(auditLogger.getEvents()).containsExactly("initialized");
        }

        assertThat(auditLogger.getEvents()).containsExactly("initialized", "destroyed");
    }
}
