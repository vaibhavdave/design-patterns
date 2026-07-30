package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/** Terminal expression: a variable name resolved against the context at interpret time. */
public final class VariableExpression implements Expression {

    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        Integer value = context.get(name);
        if (value == null) {
            throw new IllegalArgumentException("Undefined variable: " + name);
        }
        return value;
    }
}
