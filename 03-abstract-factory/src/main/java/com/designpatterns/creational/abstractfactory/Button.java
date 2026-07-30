package com.designpatterns.creational.abstractfactory;

/**
 * Abstract product #1 of the family. On its own this looks exactly like a Factory Method
 * product — the family-consistency guarantee only shows up once you look at {@link GUIFactory},
 * which forces a {@link Button} and a {@link Checkbox} to always come from the same concrete
 * factory.
 */
public interface Button {

    String render();
}
