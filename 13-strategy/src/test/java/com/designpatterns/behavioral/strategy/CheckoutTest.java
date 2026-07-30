package com.designpatterns.behavioral.strategy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckoutTest {

    @Test
    void paysUsingTheStrategyProvidedAtConstruction() {
        Checkout checkout = new Checkout(new CreditCardPaymentStrategy());

        PaymentResult result = checkout.completePurchase(100.0);

        assertThat(result.success()).isTrue();
        assertThat(result.message()).contains("credit card");
    }

    @Test
    void swappingTheStrategyOnTheSameInstanceChangesTheAlgorithmUsed() {
        Checkout checkout = new Checkout(new CreditCardPaymentStrategy());
        PaymentResult first = checkout.completePurchase(10.0);

        checkout.setPaymentStrategy(new PayPalPaymentStrategy());
        PaymentResult second = checkout.completePurchase(10.0);

        assertThat(first.message()).contains("credit card");
        assertThat(second.message()).contains("PayPal");
    }

    @Test
    void cryptoStrategyProducesACryptoSpecificMessage() {
        Checkout checkout = new Checkout(new CryptoPaymentStrategy());

        PaymentResult result = checkout.completePurchase(5.5);

        assertThat(result.message()).contains("crypto");
    }

    @Test
    void throwsWhenNoStrategyHasBeenConfigured() {
        Checkout checkout = new Checkout(null);

        assertThatThrownBy(() -> checkout.completePurchase(1.0))
                .isInstanceOf(IllegalStateException.class);
    }
}
