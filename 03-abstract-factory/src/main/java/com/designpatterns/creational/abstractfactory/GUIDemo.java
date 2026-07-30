package com.designpatterns.creational.abstractfactory;

public final class GUIDemo {

    public static void main(String[] args) {
        System.out.println("== Abstract Factory: cross-platform UI families ==");

        System.out.println("-- Windows --");
        System.out.println(new Application(new WindowsFactory()).renderUi());

        System.out.println("-- Mac --");
        System.out.println(new Application(new MacFactory()).renderUi());
    }
}
