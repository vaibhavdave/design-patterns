package com.designpatterns.capstone.di;

import java.util.UUID;

/** Deliberately a plain class with zero framework annotations — see {@link ManualDIDemo}. */
public class StripePaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(double amount) {
        return new PaymentResult(true, "stripe-" + UUID.randomUUID());
    }
}
