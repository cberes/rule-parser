package com.cberes.rule_parser;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class SymbolTable {
    private Map<String, Integer> variables = new HashMap<>();

    public void addVariable(final String name, final int value) {
        variables.put(name, value);
    }

    public OptionalInt getValue(final String name) {
        return variables.containsKey(name) ? OptionalInt.of(variables.get(name)) : OptionalInt.empty();
    }
}
