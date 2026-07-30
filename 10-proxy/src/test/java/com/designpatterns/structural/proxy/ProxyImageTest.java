package com.designpatterns.structural.proxy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProxyImageTest {

    @Test
    void realImageIsNotConstructedUntilDisplayIsFirstCalled() {
        ProxyImage proxyImage = new ProxyImage("vacation.jpg");

        assertThat(proxyImage.isLoaded()).isFalse();

        proxyImage.display();

        assertThat(proxyImage.isLoaded()).isTrue();
    }

    @Test
    void realImageIsConstructedOnlyOnceAcrossMultipleDisplayCalls() {
        ProxyImage proxyImage = new ProxyImage("vacation.jpg");

        proxyImage.display();
        boolean loadedAfterFirstDisplay = proxyImage.isLoaded();
        proxyImage.display();
        proxyImage.display();

        assertThat(loadedAfterFirstDisplay).isTrue();
        assertThat(proxyImage.isLoaded()).isTrue();
    }

    @Test
    void multipleProxiesOnlyLoadTheOnesActuallyDisplayed() {
        ProxyImage displayed = new ProxyImage("a.jpg");
        ProxyImage neverDisplayed = new ProxyImage("b.jpg");

        displayed.display();

        assertThat(displayed.isLoaded()).isTrue();
        assertThat(neverDisplayed.isLoaded()).isFalse();
    }

    @Test
    void proxyAndRealImplementationAreInterchangeableThroughTheSameInterface() {
        Image proxy = new ProxyImage("c.jpg");
        Image real = new RealImage("d.jpg");

        // Both compile and run identically through the Image reference — the caller cannot tell
        // which one it's holding.
        proxy.display();
        real.display();
    }
}
