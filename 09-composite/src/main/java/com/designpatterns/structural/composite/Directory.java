package com.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * The composite: holds children that are themselves {@link FileSystemComponent}s — either more
 * {@link FileEntry} leaves or nested {@link Directory} composites. {@link #getSize()} never needs
 * to know which; it just asks each child for its size and sums the results, and the recursion
 * takes care of arbitrary nesting depth on its own.
 */
public class Directory implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public List<FileSystemComponent> getChildren() {
        return List.copyOf(children);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        long total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();
        }
        return total;
    }

    @Override
    public String print(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent).append("+ ").append(name).append('/');
        for (FileSystemComponent child : children) {
            sb.append('\n').append(child.print(indent + "  "));
        }
        return sb.toString();
    }
}
