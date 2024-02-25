package com.cberes.rule_parser;

public interface RuleParser {
    int evaluate(String s);

    void addVariable(String name, int value);
}
