package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/** Builds one tree by hand and one via the tiny parser, then evaluates both, plus an error case. */
public final class InterpreterDemo {

    public static void main(String[] args) {
        Map<String, Integer> context = Map.of("x", 10, "y", 3);

        Expression handBuilt = new AddExpression(
                new VariableExpression("x"),
                new SubtractExpression(new NumberExpression(5), new VariableExpression("y")));
        System.out.println("Hand-built tree \"x + (5 - y)\" = " + handBuilt.interpret(context));

        Expression parsed = ExpressionParser.parse("x + 5 - y");
        System.out.println("Parsed \"x + 5 - y\" (left-to-right) = " + parsed.interpret(context));

        try {
            Expression undefined = ExpressionParser.parse("x + z");
            undefined.interpret(context);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
