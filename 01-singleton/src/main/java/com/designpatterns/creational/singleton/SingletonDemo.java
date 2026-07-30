package com.designpatterns.creational.singleton;

import com.designpatterns.creational.singleton.spring.RequestCounter;
import com.designpatterns.creational.singleton.spring.SingletonSpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** Runnable walkthrough: {@code ./gradlew :01-singleton:run} (or run this class from your IDE). */
public final class SingletonDemo {

    private SingletonDemo() {
    }

    public static void main(String[] args) {
        System.out.println("-- Hand-rolled singletons --");
        System.out.println("EnumSingleton always returns the same instance: "
                + (EnumSingleton.INSTANCE == EnumSingleton.INSTANCE));
        System.out.println("DoubleCheckedLockingSingleton same instance twice: "
                + (DoubleCheckedLockingSingleton.getInstance() == DoubleCheckedLockingSingleton.getInstance()));

        System.out.println();
        System.out.println("-- Spring-managed singleton vs. prototype scope --");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(SingletonSpringConfig.class)) {
            RequestCounter singletonA = context.getBean("singletonCounter", RequestCounter.class);
            RequestCounter singletonB = context.getBean("singletonCounter", RequestCounter.class);
            singletonA.increment();
            singletonB.increment();
            System.out.println("singleton-scoped bean is shared, count = " + singletonA.getCount()
                    + " (same instance: " + (singletonA == singletonB) + ")");

            RequestCounter prototypeA = context.getBean("prototypeCounter", RequestCounter.class);
            RequestCounter prototypeB = context.getBean("prototypeCounter", RequestCounter.class);
            System.out.println("prototype-scoped bean is fresh each time (same instance: "
                    + (prototypeA == prototypeB) + ")");
        }
    }
}
