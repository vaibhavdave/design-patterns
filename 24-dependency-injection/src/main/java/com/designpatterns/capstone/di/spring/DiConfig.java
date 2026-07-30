package com.designpatterns.capstone.di.spring;

import com.designpatterns.capstone.di.OrderService;
import com.designpatterns.capstone.di.PaymentGateway;
import com.designpatterns.capstone.di.StripePaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * None of {@code PaymentGateway}, {@code StripePaymentGateway}, or {@code OrderService} carry a
 * single Spring annotation — they're the exact same plain classes {@link
 * com.designpatterns.capstone.di.ManualDIDemo} constructs by hand. Only this configuration class
 * knows about Spring; wiring plain domain objects through {@code @Bean} methods (instead of
 * annotating every class with {@code @Component}) keeps the domain model framework-agnostic.
 * {@code OrderService}'s constructor argument is resolved by type against the
 * {@code paymentGateway} bean below — that resolution is what a container automates over
 * {@link com.designpatterns.capstone.di.ManualDIDemo}'s hand-written {@code new}.
 */
@Configuration
public class DiConfig {

    @Bean
    public PaymentGateway paymentGateway() {
        return new StripePaymentGateway();
    }

    @Bean
    public OrderService orderService(PaymentGateway paymentGateway) {
        return new OrderService(paymentGateway);
    }

    @Bean
    public AuditLogger auditLogger() {
        return new AuditLogger();
    }
}
