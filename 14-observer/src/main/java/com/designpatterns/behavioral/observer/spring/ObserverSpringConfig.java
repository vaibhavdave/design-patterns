package com.designpatterns.behavioral.observer.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** Component-scans this package to pick up {@link OrderService} and both event listeners. */
@Configuration
@ComponentScan(basePackageClasses = ObserverSpringConfig.class)
public class ObserverSpringConfig {
}
