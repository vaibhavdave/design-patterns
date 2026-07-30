package com.designpatterns.structural.facade;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }
}
