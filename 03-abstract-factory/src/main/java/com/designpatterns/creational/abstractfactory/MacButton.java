package com.designpatterns.creational.abstractfactory;

public final class MacButton implements Button {

    @Override
    public String render() {
        return "[Mac Button: rounded, translucent]";
    }
}
