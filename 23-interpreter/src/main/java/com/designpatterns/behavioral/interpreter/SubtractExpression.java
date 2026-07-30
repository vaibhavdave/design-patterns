package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/** Non-terminal expression: combines two sub-expressions with subtraction. */
public final class SubtractExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) - right.interpret(context);
    }
}
