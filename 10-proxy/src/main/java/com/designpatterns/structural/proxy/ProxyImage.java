package com.designpatterns.structural.proxy;

/**
 * A virtual proxy: it implements the same {@link Image} interface as {@link RealImage} but does
 * not construct one until {@link #display()} is actually called for the first time, then caches
 * it for every later call. A client that constructs a hundred {@code ProxyImage}s but only ever
 * displays three pays the real loading cost for exactly three.
 */
public class ProxyImage implements Image {

    private final String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }

    /**
     * Test/inspection hook — not part of the {@link Image} contract. Lets callers assert that no
     * {@link RealImage} has been constructed yet without needing to intercept stdout.
     */
    boolean isLoaded() {
        return realImage != null;
    }
}
