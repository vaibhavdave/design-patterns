package com.designpatterns.behavioral.observer;

import com.designpatterns.behavioral.observer.spring.AnalyticsEventListener;
import com.designpatterns.behavioral.observer.spring.EmailEventListener;
import com.designpatterns.behavioral.observer.spring.ObserverSpringConfig;
import com.designpatterns.behavioral.observer.spring.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** Runnable walkthrough: {@code ./gradlew :14-observer:run} (or run this class from your IDE). */
public final class OrderDemo {

    private OrderDemo() {
    }

    public static void main(String[] args) {
        System.out.println("-- Plain GoF Observer --");
        Order order = new Order("ORD-1");
        EmailNotifierObserver emailObserver = new EmailNotifierObserver();
        AnalyticsObserver analyticsObserver = new AnalyticsObserver();
        order.addObserver(emailObserver);
        order.addObserver(analyticsObserver);

        order.setStatus("PAID");
        order.setStatus("SHIPPED");

        emailObserver.getSentEmails().forEach(System.out::println);
        analyticsObserver.getRecordedEvents().forEach(System.out::println);

        System.out.println();
        System.out.println("-- Spring: ApplicationEventPublisher / @EventListener --");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(ObserverSpringConfig.class)) {
            OrderService orderService = context.getBean(OrderService.class);
            EmailEventListener emailListener = context.getBean(EmailEventListener.class);
            AnalyticsEventListener analyticsListener = context.getBean(AnalyticsEventListener.class);

            orderService.changeStatus("ORD-2", "PAID");
            orderService.changeStatus("ORD-2", "SHIPPED");

            emailListener.getSentEmails().forEach(System.out::println);
            analyticsListener.getRecordedEvents().forEach(System.out::println);
        }
    }
}
