package com.designpatterns.behavioral.observer.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ObserverSpringConfig.class);
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void publishingAnEventReachesAllRegisteredListenersWithoutDirectReferences() {
        OrderService orderService = context.getBean(OrderService.class);
        EmailEventListener emailListener = context.getBean(EmailEventListener.class);
        AnalyticsEventListener analyticsListener = context.getBean(AnalyticsEventListener.class);

        orderService.changeStatus("ORD-9", "SHIPPED");

        assertThat(emailListener.getSentEmails())
                .containsExactly("Email to customer: order ORD-9 is now SHIPPED");
        assertThat(analyticsListener.getRecordedEvents()).containsExactly("ORD-9:SHIPPED");
    }

    @Test
    void multipleEventsAreDeliveredToListenersInPublishOrder() {
        OrderService orderService = context.getBean(OrderService.class);
        AnalyticsEventListener analyticsListener = context.getBean(AnalyticsEventListener.class);

        orderService.changeStatus("ORD-9", "PAID");
        orderService.changeStatus("ORD-9", "SHIPPED");

        assertThat(analyticsListener.getRecordedEvents())
                .containsExactly("ORD-9:PAID", "ORD-9:SHIPPED");
    }
}
