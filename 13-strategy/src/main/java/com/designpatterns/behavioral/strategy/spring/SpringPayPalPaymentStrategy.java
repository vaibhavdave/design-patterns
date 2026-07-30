package com.designpatterns.behavioral.strategy.spring;

import com.designpatterns.behavioral.strategy.PayPalPaymentStrategy;
import com.designpatterns.behavioral.strategy.PaymentResult;
import com.designpatterns.behavioral.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

/** Registered under the bean name {@code "payPal"}. */
@Component("payPal")
public class SpringPayPalPaymentStrategy implements PaymentStrategy {

    private final PaymentStrategy delegate = new PayPalPaymentStrategy();

    @Override
    public PaymentResult pay(double amount) {
        return delegate.pay(amount);
    }
}
