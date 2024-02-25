package com.cberes.rule_parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

abstract class RuleParserTest {
    abstract RuleParser parser();

    @Test
    public void testSingleVariable() {
        final RuleParser ruleParser = parser();
        ruleParser.addVariable("foo", 5);
        assertEquals(5, ruleParser.evaluate("foo"));
    }

    @Test
    public void testAddVariableToLiteral() {
        final RuleParser ruleParser = parser();
        ruleParser.addVariable("foo", 5);
        assertEquals(7, ruleParser.evaluate("foo + 2"));
    }

    @Test
    public void testAddTwoVariables() {
        final RuleParser ruleParser = parser();
        ruleParser.addVariable("foo", 5);
        ruleParser.addVariable("a", 3);
        assertEquals(8, ruleParser.evaluate("foo + a"));
    }

    @Test
    public void testAddLeftNestedExpression() {
        final RuleParser ruleParser = parser();
        ruleParser.addVariable("a", 5);
        ruleParser.addVariable("b", 3);
        assertEquals(20, ruleParser.evaluate("(a * b) + 5"));
    }

    @Test
    public void testAddRightNestedExpression() {
        final RuleParser ruleParser = parser();
        ruleParser.addVariable("c", -10);
        ruleParser.addVariable("d", 2);
        assertEquals(11, ruleParser.evaluate("6 - (c / d)"));
    }

    @Test
    public void testAddTwoNestedExpressions() {
        final RuleParser ruleParser = parser();
        ruleParser.addVariable("a", 5);
        ruleParser.addVariable("b", 3);
        ruleParser.addVariable("c", -10);
        ruleParser.addVariable("d", 2);
        assertEquals(20, ruleParser.evaluate("(a * b) - (c / d)"));
    }
}
