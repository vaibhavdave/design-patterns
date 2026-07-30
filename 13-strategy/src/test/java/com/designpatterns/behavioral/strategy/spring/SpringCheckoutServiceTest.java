package com.designpatterns.behavioral.strategy.spring;

import com.designpatterns.behavioral.strategy.PaymentResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpringCheckoutServiceTest {

    private AnnotationConfigApplicationContext context;
    private SpringCheckoutService service;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(StrategySpringConfig.class);
        service = context.getBean(SpringCheckoutService.class);
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void containerRegistersAllThreeStrategyBeansIntoTheMap() {
        assertThat(service.registeredStrategyCount()).isEqualTo(3);
    }

    @Test
    void looksUpTheCreditCardStrategyByBeanName() {
        PaymentResult result = service.checkout("creditCard", 25.0);

        assertThat(result.success()).isTrue();
        assertThat(result.message()).contains("credit card");
    }

    @Test
    void looksUpThePayPalStrategyByBeanName() {
        PaymentResult result = service.checkout("payPal", 25.0);

        assertThat(result.message()).contains("PayPal");
    }

    @Test
    void throwsForAnUnknownStrategyKey() {
        assertThatThrownBy(() -> service.checkout("bitcoinCash", 10.0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
