package com.designpatterns.behavioral.interpreter;

/**
 * A deliberately minimal parser: it only handles a space-separated infix expression like
 * {@code "x + 5 - y"} (single-character operators, tokens separated by whitespace, no precedence
 * or parentheses to worry about since only + and - exist). It folds left-to-right, which is
 * correct for a grammar with only left-associative operators of equal precedence.
 *
 * <p>This is a toy on purpose — real systems reach for a proper tokenizer/grammar (see the
 * README) once precedence, parentheses, or more operators enter the picture.
 */
public final class ExpressionParser {

    private ExpressionParser() {
    }

    public static Expression parse(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        if (tokens.length == 0 || tokens[0].isEmpty() || tokens.length % 2 == 0) {
            throw new IllegalArgumentException("Malformed expression: " + expression);
        }

        Expression result = toOperand(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            Expression right = toOperand(tokens[i + 1]);
            result = switch (operator) {
                case "+" -> new AddExpression(result, right);
                case "-" -> new SubtractExpression(result, right);
                default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
            };
        }
        return result;
    }

    private static Expression toOperand(String token) {
        if (token.chars().allMatch(Character::isDigit)) {
            return new NumberExpression(Integer.parseInt(token));
        }
        return new VariableExpression(token);
    }
}
