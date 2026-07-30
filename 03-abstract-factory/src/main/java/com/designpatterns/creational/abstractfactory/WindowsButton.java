package com.designpatterns.creational.abstractfactory;

public final class WindowsButton implements Button {

    @Override
    public String render() {
        return "[Windows Button: rectangular, flat]";
    }
}
