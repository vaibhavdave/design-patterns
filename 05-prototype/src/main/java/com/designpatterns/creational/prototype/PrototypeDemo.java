package com.designpatterns.creational.prototype;

public final class PrototypeDemo {

    public static void main(String[] args) {
        System.out.println("== Prototype cache: copying instead of constructing ==");
        ShapePrototypeCache cache = ShapePrototypeCache.withDefaults();

        Shape circleA = cache.get("default-circle");
        Shape circleB = cache.get("default-circle");
        System.out.println("circleA: " + circleA.describe());
        System.out.println("circleB: " + circleB.describe());
        System.out.println("circleA and circleB are independent instances: " + (circleA != circleB));

        System.out.println();
        System.out.println("== Deep copy protects the original ==");
        Circle original = new Circle(new Point(5, 5), 2.0, "red");
        Circle clone = original.copy();
        clone.center().moveTo(99, 99);
        System.out.println("original stays at " + original.center() + " (correct deep copy)");
        System.out.println("clone moved to    " + clone.center());

        System.out.println();
        System.out.println("== The shallow-copy bug, for contrast ==");
        BuggyShallowCircle buggyOriginal = new BuggyShallowCircle(new Point(5, 5), 2.0);
        BuggyShallowCircle buggyClone = buggyOriginal.copy();
        buggyClone.center().moveTo(99, 99);
        System.out.println("buggyOriginal ALSO moved to " + buggyOriginal.center()
                + " -- shared Point reference, this is the bug");
    }
}
