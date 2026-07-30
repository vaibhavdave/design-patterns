package com.designpatterns.capstone.di;

/**
 * "Poor man's DI": no framework at all, just a composition root — one place, as close to
 * {@code main} as possible, that knows about concrete classes and wires them together. Every
 * other class in this package only ever sees {@link PaymentGateway}, never {@link
 * StripePaymentGateway} or {@link PaypalPaymentGateway} directly. This is DI in its purest form;
 * a container automates exactly this wiring at scale (see {@code spring/DiConfig}).
 */
public final class ManualDIDemo {

    private ManualDIDemo() {
    }

    public static void run() {
        OrderService stripeOrders = new OrderService(new StripePaymentGateway());
        OrderService paypalOrders = new OrderService(new PaypalPaymentGateway());

        System.out.println("Manual DI (composition root wires concrete classes by hand):");
        System.out.println("  " + stripeOrders.placeOrder(42.00));
        System.out.println("  " + paypalOrders.placeOrder(17.50));
    }
}
