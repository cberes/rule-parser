package com.cberes.rule_parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

class Operator {
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS;

    static {
        OPERATIONS = new HashMap<>();
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
        OPERATIONS.put("^", (a, b) -> (int) Math.pow(a, b));
    }

    private final String text;

    Operator(final String text) {
        this.text = text;
    }

    int apply(final int left, final int right) {
        if (isOperator(text)) {
            return OPERATIONS.get(text).apply(left, right);
        } else {
            throw new UnsupportedOperationException("Unknown operator: " + text);
        }
    }

    static boolean isOperator(final String s) {
        return OPERATIONS.containsKey(s);
    }
}
