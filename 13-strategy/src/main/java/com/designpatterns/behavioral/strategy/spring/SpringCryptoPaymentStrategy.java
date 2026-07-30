package com.designpatterns.behavioral.strategy.spring;

import com.designpatterns.behavioral.strategy.CryptoPaymentStrategy;
import com.designpatterns.behavioral.strategy.PaymentResult;
import com.designpatterns.behavioral.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

/** Registered under the bean name {@code "crypto"}. */
@Component("crypto")
public class SpringCryptoPaymentStrategy implements PaymentStrategy {

    private final PaymentStrategy delegate = new CryptoPaymentStrategy();

    @Override
    public PaymentResult pay(double amount) {
        return delegate.pay(amount);
    }
}
