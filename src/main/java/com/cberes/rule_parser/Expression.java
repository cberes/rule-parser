package com.cberes.rule_parser;

public interface Expression {
    int evaluateWith(SymbolTable symbols);
}
