package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/** Every node in the expression tree — terminal or non-terminal — implements this the same way. */
public interface Expression {

    int interpret(Map<String, Integer> context);
}
