package com.designpatterns.creational.singleton.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * {@code singletonCounter} shows Spring's default bean scope: one instance per container, shared
 * by every injection point — no {@code getInstance()} or private constructor required. {@code
 * prototypeCounter} is the deliberate counter-example: a new instance is created every time it's
 * requested from the context, which is what you'd reach for if you actually needed
 * Gang-of-Four Prototype semantics inside a Spring app instead of Singleton.
 */
@Configuration
public class SingletonSpringConfig {

    @Bean
    public RequestCounter singletonCounter() {
        return new RequestCounter();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RequestCounter prototypeCounter() {
        return new RequestCounter();
    }
}
