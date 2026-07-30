package com.designpatterns.structural.composite;

public final class FileSystemDemo {

    private FileSystemDemo() {
    }

    public static void main(String[] args) {
        Directory root = new Directory("root");

        Directory src = new Directory("src");
        src.add(new FileEntry("Main.java", 1200));
        src.add(new FileEntry("Utils.java", 800));

        Directory nested = new Directory("nested");
        nested.add(new FileEntry("Deep.java", 400));
        src.add(nested);

        Directory docs = new Directory("docs");
        docs.add(new FileEntry("README.md", 2500));

        root.add(src);
        root.add(docs);
        root.add(new FileEntry("build.gradle.kts", 300));

        System.out.println(root.print(""));
        System.out.println();
        System.out.println("Total size: " + root.getSize() + " bytes");
    }
}
