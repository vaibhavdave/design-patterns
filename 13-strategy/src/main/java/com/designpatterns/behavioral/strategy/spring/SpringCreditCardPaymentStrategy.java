package com.designpatterns.behavioral.strategy.spring;

import com.designpatterns.behavioral.strategy.CreditCardPaymentStrategy;
import com.designpatterns.behavioral.strategy.PaymentResult;
import com.designpatterns.behavioral.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

/**
 * Registered under the bean name {@code "creditCard"} — that name is the key
 * {@link SpringCheckoutService} looks the strategy up by. Delegates to the plain GoF
 * implementation rather than duplicating its logic.
 */
@Component("creditCard")
public class SpringCreditCardPaymentStrategy implements PaymentStrategy {

    private final PaymentStrategy delegate = new CreditCardPaymentStrategy();

    @Override
    public PaymentResult pay(double amount) {
        return delegate.pay(amount);
    }
}
