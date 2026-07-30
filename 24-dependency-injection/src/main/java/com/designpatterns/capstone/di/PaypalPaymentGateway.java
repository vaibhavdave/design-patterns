package com.designpatterns.capstone.di;

import java.util.UUID;

public class PaypalPaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(double amount) {
        return new PaymentResult(true, "paypal-" + UUID.randomUUID());
    }
}
