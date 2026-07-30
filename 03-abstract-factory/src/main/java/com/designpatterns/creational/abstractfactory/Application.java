package com.designpatterns.creational.abstractfactory;

/**
 * The client. Holds only a {@link GUIFactory} reference — it never imports
 * {@code WindowsButton}, {@code MacCheckbox}, or any other concrete type, so it works unmodified
 * for every current and future platform family.
 */
public final class Application {

    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public String renderUi() {
        return button.render() + "\n" + checkbox.render();
    }
}
