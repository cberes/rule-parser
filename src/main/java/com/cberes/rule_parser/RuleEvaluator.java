package com.cberes.rule_parser;

public class RuleEvaluator implements RuleParser {
    private final RuleParserLite parser;
    private final SymbolTable symbols;

    public RuleEvaluator(final RuleParserLite parser, final SymbolTable symbols) {
        this.parser = parser;
        this.symbols = symbols;
    }

    @Override
    public int evaluate(final String s) {
        return parser.parse(s).evaluateWith(symbols);
    }

    @Override
    public void addVariable(final String name, final int value) {
        symbols.addVariable(name, value);
    }
}
