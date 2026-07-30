package com.designpatterns.structural.composite;

/**
 * The leaf: a plain file with a fixed size and no children. Recursion in {@link Directory#getSize()}
 * bottoms out here.
 */
public class FileEntry implements FileSystemComponent {

    private final String name;
    private final long size;

    public FileEntry(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public String print(String indent) {
        return indent + "- " + name + " (" + size + " bytes)";
    }
}
