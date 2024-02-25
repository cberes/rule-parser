package com.cberes.rule_parser;

import java.util.NoSuchElementException;

class VariableExpression implements Expression {
    private final String name;

    VariableExpression(final String name) {
        this.name = name;
    }

    @Override
    public int evaluateWith(final SymbolTable symbols) {
        return symbols.getValue(name)
                .orElseThrow(() -> new NoSuchElementException("No such variable: " + name));
    }
}
