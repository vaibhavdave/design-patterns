package com.designpatterns.behavioral.nullobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class NullLoggerTest {

    private final Logger nullLogger = NullLogger.INSTANCE;

    @Test
    void isNotEnabled() {
        assertThat(nullLogger.isEnabled()).isFalse();
    }

    @Test
    void infoDoesNotThrow() {
        assertThatNoException().isThrownBy(() -> nullLogger.info("anything"));
    }

    @Test
    void warnDoesNotThrow() {
        assertThatNoException().isThrownBy(() -> nullLogger.warn("anything"));
    }

    @Test
    void errorDoesNotThrow() {
        assertThatNoException().isThrownBy(
                () -> nullLogger.error("oops", new RuntimeException("boom")));
    }
}
