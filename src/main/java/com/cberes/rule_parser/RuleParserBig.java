package com.cberes.rule_parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RuleParserBig implements RuleParser {
    private String current;
    private Map<String, Integer> variables = new HashMap<>();
    private int currentPosition;

    @Override
    public int evaluate(final String s) {
        current = s.trim();
        currentPosition = 0;

        Node left = null;
        Operator op = null;
        int value = 0;
        while (hasMoreTerms()) {
            String term = nextTerm();
            if (left == null) {
                left = new Node(term);
            } else if (op == null) {
                op = new Operator(term);
            } else {
                Node right = new Node(term);

                value += branchingExpression(left, op, right);

                left = null;
                op = null;
            }
        }

        if (left != null) {
            value += variableExpression(left);
        }

        return value;
    }

    @Override
    public void addVariable(final String name, final int value) {
        variables.put(name, value);
    }

    private int branchingExpression(final Node left, final Operator op, final Node right) {
        if (left.isLiteral() && right.isLiteral()) {
            return causalExpression(left, op, right);
        } else {
            final RuleParserBig ruleParser = new RuleParserBig();
            variables.forEach(ruleParser::addVariable);

            final int leftValue = ruleParser.evaluate(left.text());
            final int rightValue = ruleParser.evaluate(right.text());
            return op.apply(leftValue, rightValue);
        }
    }

    private int causalExpression(final Node left, final Operator op, final Node right) {
        final int leftValue = variableExpression(left);
        final int rightValue = variableExpression(right);
        return op.apply(leftValue, rightValue);
    }

    private int variableExpression(final Node node) {
        final Integer value = variables.get(node.text());
        return value != null ? value : valueExpression(node);
    }

    private int valueExpression(final Node node) {
        return Integer.parseInt(node.text());
    }

    private String nextTerm() {
        final boolean atParenthesis = current.charAt(currentPosition) == '(';
        final char seekingChar = atParenthesis ? ')' : ' ';
        final int endOfCurrentTerm = Optional.of(current.indexOf(seekingChar, currentPosition))
                .filter(i -> i >= 0)
                .orElseGet(current::length);

        final int offset = atParenthesis ? 1 : 0;
        final String nextTerm = current.substring(currentPosition + offset, endOfCurrentTerm);
        currentPosition = endOfCurrentTerm + offset;

        while (currentPosition < current.length() && current.charAt(currentPosition) == ' ') {
            currentPosition++;
        }

        return nextTerm;
    }

    private boolean hasMoreTerms() {
        return current != null && currentPosition < current.length();
    }
}
