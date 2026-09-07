package com.designpatterns.behavioral.nullobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleLoggerTest {

    private final Logger logger = new ConsoleLogger("test");

    @Test
    void isEnabled() {
        assertThat(logger.isEnabled()).isTrue();
    }

    @Test
    void nullLoggerIsSingleton() {
        assertThat(NullLogger.INSTANCE).isSameAs(NullLogger.INSTANCE);
    }
}
