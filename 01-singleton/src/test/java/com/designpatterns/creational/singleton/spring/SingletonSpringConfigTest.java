package com.designpatterns.creational.singleton.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonSpringConfigTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(SingletonSpringConfig.class);
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void defaultScopeSharesOneInstanceAcrossLookups() {
        RequestCounter first = context.getBean("singletonCounter", RequestCounter.class);
        RequestCounter second = context.getBean("singletonCounter", RequestCounter.class);

        assertThat(first).isSameAs(second);

        first.increment();
        assertThat(second.getCount()).isEqualTo(1);
    }

    @Test
    void prototypeScopeCreatesFreshInstancePerLookup() {
        RequestCounter first = context.getBean("prototypeCounter", RequestCounter.class);
        RequestCounter second = context.getBean("prototypeCounter", RequestCounter.class);

        assertThat(first).isNotSameAs(second);

        first.increment();
        assertThat(second.getCount()).isZero();
    }
}
