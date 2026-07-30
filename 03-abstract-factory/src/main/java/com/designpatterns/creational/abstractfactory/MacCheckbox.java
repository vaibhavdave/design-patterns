package com.designpatterns.creational.abstractfactory;

public final class MacCheckbox implements Checkbox {

    @Override
    public String render() {
        return "[Mac Checkbox: rounded tick]";
    }
}
