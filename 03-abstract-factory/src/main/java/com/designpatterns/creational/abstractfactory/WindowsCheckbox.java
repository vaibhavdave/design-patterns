package com.designpatterns.creational.abstractfactory;

public final class WindowsCheckbox implements Checkbox {

    @Override
    public String render() {
        return "[Windows Checkbox: square tick]";
    }
}
