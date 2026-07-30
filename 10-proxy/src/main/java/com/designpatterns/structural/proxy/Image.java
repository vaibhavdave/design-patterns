package com.designpatterns.structural.proxy;

/**
 * The subject interface. Both {@link RealImage} and {@link ProxyImage} implement it, so a client
 * holding an {@code Image} reference cannot tell which one it has.
 */
public interface Image {

    void display();
}
