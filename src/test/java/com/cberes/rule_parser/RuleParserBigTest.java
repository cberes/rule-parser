package com.cberes.rule_parser;

public class RuleParserBigTest extends RuleParserTest {
    @Override
    RuleParser parser() {
        return new RuleParserBig();
    }
}
