package com.designpatterns.structural.proxy;

/**
 * Stands in for something genuinely expensive to create — loading a large file from disk, opening
 * a network connection, decoding a big image. The constructor simulates that cost so tests can
 * observe exactly when (and whether) it runs.
 */
public class RealImage implements Image {

    private final String fileName;
    private final long loadedAtNanos;

    public RealImage(String fileName) {
        this.fileName = fileName;
        // Simulated expensive load from disk.
        System.out.println("Loading " + fileName + " from disk...");
        this.loadedAtNanos = System.nanoTime();
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    public long getLoadedAtNanos() {
        return loadedAtNanos;
    }
}
