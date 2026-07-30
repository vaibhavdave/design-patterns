package com.designpatterns.creational.abstractfactory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GUIFactoryTest {

    @Test
    void windowsFactoryProducesOnlyWindowsFamilyMembers() {
        GUIFactory factory = new WindowsFactory();

        assertThat(factory.createButton()).isInstanceOf(WindowsButton.class);
        assertThat(factory.createCheckbox()).isInstanceOf(WindowsCheckbox.class);
    }

    @Test
    void macFactoryProducesOnlyMacFamilyMembers() {
        GUIFactory factory = new MacFactory();

        assertThat(factory.createButton()).isInstanceOf(MacButton.class);
        assertThat(factory.createCheckbox()).isInstanceOf(MacCheckbox.class);
    }

    @Test
    void applicationRendersAConsistentFamilyRegardlessOfWhichFactoryItReceives() {
        Application windowsApp = new Application(new WindowsFactory());
        Application macApp = new Application(new MacFactory());

        assertThat(windowsApp.renderUi()).contains("Windows Button").contains("Windows Checkbox");
        assertThat(macApp.renderUi()).contains("Mac Button").contains("Mac Checkbox");
    }

    @Test
    void applicationNeverMixesFamiliesAcrossDifferentFactoryInstances() {
        String windowsUi = new Application(new WindowsFactory()).renderUi();
        String macUi = new Application(new MacFactory()).renderUi();

        assertThat(windowsUi).doesNotContain("Mac");
        assertThat(macUi).doesNotContain("Windows");
    }
}
