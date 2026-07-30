package com.designpatterns.behavioral.strategy.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** Component-scans this package so the {@code @Component}-annotated strategies and the service
 * that consumes them are all picked up automatically. */
@Configuration
@ComponentScan(basePackageClasses = StrategySpringConfig.class)
public class StrategySpringConfig {
}
