package com.designpatterns.behavioral.strategy;

import com.designpatterns.behavioral.strategy.spring.SpringCheckoutService;
import com.designpatterns.behavioral.strategy.spring.StrategySpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** Runnable walkthrough: {@code ./gradlew :13-strategy:run} (or run this class from your IDE). */
public final class CheckoutDemo {

    private CheckoutDemo() {
    }

    public static void main(String[] args) {
        System.out.println("-- Plain GoF Strategy --");
        Checkout checkout = new Checkout(new CreditCardPaymentStrategy());
        System.out.println(checkout.completePurchase(49.99).message());

        checkout.setPaymentStrategy(new PayPalPaymentStrategy());
        System.out.println(checkout.completePurchase(49.99).message());

        checkout.setPaymentStrategy(new CryptoPaymentStrategy());
        System.out.println(checkout.completePurchase(49.99).message());

        System.out.println();
        System.out.println("-- Spring: strategies looked up from a Map<String, PaymentStrategy> --");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(StrategySpringConfig.class)) {
            SpringCheckoutService service = context.getBean(SpringCheckoutService.class);
            System.out.println("Registered strategies: " + service.registeredStrategyCount());
            System.out.println(service.checkout("creditCard", 19.99).message());
            System.out.println(service.checkout("payPal", 19.99).message());
            System.out.println(service.checkout("crypto", 19.99).message());
        }
    }
}
