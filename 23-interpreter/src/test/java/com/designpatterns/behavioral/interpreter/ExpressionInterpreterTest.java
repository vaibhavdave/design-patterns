package com.designpatterns.behavioral.interpreter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExpressionInterpreterTest {

    @Test
    void evaluatesSimpleAdditionExpression() {
        Expression expression = new AddExpression(new VariableExpression("x"), new NumberExpression(5));

        int result = expression.interpret(Map.of("x", 10));

        assertThat(result).isEqualTo(15);
    }

    @Test
    void evaluatesSimpleSubtractionExpression() {
        Expression expression = new SubtractExpression(new VariableExpression("x"), new NumberExpression(4));

        int result = expression.interpret(Map.of("x", 10));

        assertThat(result).isEqualTo(6);
    }

    @Test
    void evaluatesNestedAddAndSubtractExpression() {
        // x + (5 - y), with x=10, y=3 -> 10 + (5 - 3) = 12
        Expression expression = new AddExpression(
                new VariableExpression("x"),
                new SubtractExpression(new NumberExpression(5), new VariableExpression("y")));

        int result = expression.interpret(Map.of("x", 10, "y", 3));

        assertThat(result).isEqualTo(12);
    }

    @Test
    void parserBuildsAnEquivalentTreeFromAnInfixString() {
        Expression parsed = ExpressionParser.parse("x + 5 - y");

        int result = parsed.interpret(Map.of("x", 10, "y", 3));

        // Left-to-right folding: (x + 5) - y = (10 + 5) - 3 = 12
        assertThat(result).isEqualTo(12);
    }

    @Test
    void parserHandlesMultipleOperatorsInSequence() {
        Expression parsed = ExpressionParser.parse("10 - 2 + 3 - 1");

        int result = parsed.interpret(Map.of());

        assertThat(result).isEqualTo(10);
    }

    @Test
    void undefinedVariableThrowsIllegalArgumentException() {
        Expression expression = new VariableExpression("z");

        assertThatThrownBy(() -> expression.interpret(Map.of("x", 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("z");
    }
}
