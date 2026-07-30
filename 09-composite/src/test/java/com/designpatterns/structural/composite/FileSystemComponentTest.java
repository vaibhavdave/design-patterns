package com.designpatterns.structural.composite;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileSystemComponentTest {

    @Test
    void leafReportsItsOwnFixedSize() {
        FileEntry file = new FileEntry("a.txt", 100);

        assertThat(file.getSize()).isEqualTo(100);
    }

    @Test
    void directoryRecursivelySumsSizesOfDirectChildren() {
        Directory dir = new Directory("root");
        dir.add(new FileEntry("a.txt", 100));
        dir.add(new FileEntry("b.txt", 200));

        assertThat(dir.getSize()).isEqualTo(300);
    }

    @Test
    void directorySizeRecursesThroughMultipleNestingLevels() {
        Directory level3 = new Directory("level3");
        level3.add(new FileEntry("deep.txt", 50));

        Directory level2 = new Directory("level2");
        level2.add(new FileEntry("mid.txt", 30));
        level2.add(level3);

        Directory level1 = new Directory("level1");
        level1.add(new FileEntry("top.txt", 10));
        level1.add(level2);

        assertThat(level1.getSize()).isEqualTo(10 + 30 + 50);
    }

    @Test
    void leafAndCompositeAreTreatedUniformlyThroughTheSameInterface() {
        // The whole point of Composite: client code holds a FileSystemComponent and never needs
        // to check whether it's a leaf or a composite.
        FileSystemComponent leaf = new FileEntry("solo.txt", 42);
        FileSystemComponent composite = new Directory("empty");

        assertThat(leaf.getSize()).isEqualTo(42);
        assertThat(composite.getSize()).isEqualTo(0);
        assertThat(leaf.getName()).isEqualTo("solo.txt");
        assertThat(composite.getName()).isEqualTo("empty");
    }

    @Test
    void removingAChildExcludesItFromSizeAndChildrenList() {
        Directory dir = new Directory("root");
        FileEntry toRemove = new FileEntry("temp.txt", 999);
        dir.add(new FileEntry("keep.txt", 1));
        dir.add(toRemove);

        dir.remove(toRemove);

        assertThat(dir.getSize()).isEqualTo(1);
        assertThat(dir.getChildren()).hasSize(1).noneMatch(c -> c.getName().equals("temp.txt"));
    }
}
