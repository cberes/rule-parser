package com.cberes.rule_parser;

public class RuleEvaluatorTest extends RuleParserTest {
    @Override
    RuleParser parser() {
        return new RuleEvaluator(new RuleParserLite(), new SymbolTable());
    }
}
