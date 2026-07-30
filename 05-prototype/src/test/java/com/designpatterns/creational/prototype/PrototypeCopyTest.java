package com.designpatterns.creational.prototype;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrototypeCopyTest {

    @Test
    void circleCopyIsAnIndependentButEqualInstance() {
        Circle original = new Circle(new Point(1, 2), 3.0, "blue");

        Circle copy = original.copy();

        assertThat(copy).isNotSameAs(original).isEqualTo(original);
    }

    @Test
    void mutatingTheCopysNestedPointDoesNotAffectTheOriginal() {
        Circle original = new Circle(new Point(1, 2), 3.0, "blue");
        Circle copy = original.copy();

        copy.center().moveTo(50, 60);

        assertThat(original.center().x()).isEqualTo(1);
        assertThat(original.center().y()).isEqualTo(2);
        assertThat(copy.center().x()).isEqualTo(50);
        assertThat(copy.center().y()).isEqualTo(60);
    }

    @Test
    void rectangleCopyDeepCopiesItsTagsListToo() {
        Rectangle original = new Rectangle(new Point(0, 0), 4.0, 2.0, List.of("a", "b"));
        Rectangle copy = original.copy();

        copy.tags().add("injected-into-copy-only");

        assertThat(original.tags()).containsExactly("a", "b");
        assertThat(copy.tags()).containsExactly("a", "b", "injected-into-copy-only");
    }

    @Test
    void shallowCopyBugMutatingTheCloneAlsoMovesTheOriginal() {
        // This test PROVES the bug exists in BuggyShallowCircle, as a deliberate contrast with
        // the correct Circle#copy() behavior asserted above.
        BuggyShallowCircle original = new BuggyShallowCircle(new Point(1, 2), 3.0);
        BuggyShallowCircle copy = original.copy();

        copy.center().moveTo(50, 60);

        assertThat(original.center().x()).isEqualTo(50);
        assertThat(original.center().y()).isEqualTo(60);
        assertThat(original.center()).isSameAs(copy.center());
    }

    @Test
    void prototypeCacheReturnsIndependentCopiesOfTheRegisteredDefaults() {
        ShapePrototypeCache cache = ShapePrototypeCache.withDefaults();

        Shape first = cache.get("default-circle");
        Shape second = cache.get("default-circle");

        assertThat(first).isNotSameAs(second).isEqualTo(second);
    }
}
