package com.designpatterns.capstone.di;

/** The abstraction {@link OrderService} depends on — never a concrete gateway. */
public interface PaymentGateway {

    PaymentResult charge(double amount);

    record PaymentResult(boolean success, String reference) {
    }
}
