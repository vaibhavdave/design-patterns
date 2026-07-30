package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/** Terminal expression: an integer literal. Ignores the context entirely. */
public final class NumberExpression implements Expression {

    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return value;
    }
}
