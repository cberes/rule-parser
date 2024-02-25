package com.cberes.rule_parser;

class ValueExpression implements Expression {
    private final int value;

    ValueExpression(final String value) {
        this(Integer.parseInt(value));
    }

    ValueExpression(final int value) {
        this.value = value;
    }

    @Override
    public int evaluateWith(final SymbolTable symbols) {
        return value;
    }
}
