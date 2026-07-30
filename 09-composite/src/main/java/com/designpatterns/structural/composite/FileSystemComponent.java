package com.designpatterns.structural.composite;

/**
 * The component interface shared by leaves ({@link FileEntry}) and composites ({@link
 * Directory}), so client code can call {@code getSize()} / {@code print()} on either one without
 * caring which it's holding.
 */
public interface FileSystemComponent {

    String getName();

    long getSize();

    String print(String indent);
}
