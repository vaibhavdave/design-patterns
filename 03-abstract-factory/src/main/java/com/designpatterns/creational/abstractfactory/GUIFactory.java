package com.designpatterns.creational.abstractfactory;

/**
 * The Abstract Factory. One method per product in the family. Because both methods live on the
 * same factory instance, it is structurally impossible to ask a {@code WindowsFactory} for a
 * {@link Checkbox} and get a {@link MacCheckbox} back — the whole point of this pattern is that
 * guarantee.
 */
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
