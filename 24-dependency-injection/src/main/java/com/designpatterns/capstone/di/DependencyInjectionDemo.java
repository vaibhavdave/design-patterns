package com.designpatterns.capstone.di;

import com.designpatterns.capstone.di.spring.AuditLogger;
import com.designpatterns.capstone.di.spring.DiConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class DependencyInjectionDemo {

    private DependencyInjectionDemo() {
    }

    public static void main(String[] args) {
        ManualDIDemo.run();

        System.out.println();
        System.out.println("Spring-managed DI (container resolves and injects OrderService's dependency):");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(DiConfig.class)) {
            OrderService orderService = context.getBean(OrderService.class);
            System.out.println("  " + orderService.placeOrder(99.99));

            AuditLogger auditLogger = context.getBean(AuditLogger.class);
            auditLogger.log("order placed");
            System.out.println("  AuditLogger lifecycle so far: " + auditLogger.getEvents());
        }
    }
}
