package com.designpatterns.behavioral.strategy.spring;

import com.designpatterns.behavioral.strategy.PaymentResult;
import com.designpatterns.behavioral.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Spring auto-populates a constructor parameter of type {@code Map<String, PaymentStrategy>}
 * with every {@link PaymentStrategy} bean in the context, keyed by bean name. Looking a strategy
 * up by key ({@code strategiesByName.get("creditCard")}) replaces a hand-written if/else or
 * switch over concrete types — the container did the registration.
 */
@Service
public class SpringCheckoutService {

    private final Map<String, PaymentStrategy> strategiesByName;

    public SpringCheckoutService(Map<String, PaymentStrategy> strategiesByName) {
        this.strategiesByName = strategiesByName;
    }

    public PaymentResult checkout(String strategyName, double amount) {
        PaymentStrategy strategy = strategiesByName.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("No payment strategy registered under: " + strategyName);
        }
        return strategy.pay(amount);
    }

    public int registeredStrategyCount() {
        return strategiesByName.size();
    }
}
