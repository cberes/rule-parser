package com.cberes.rule_parser;

public class RuleParserLite {
    public Expression parse(final String s) {
        final TermTokenizer terms = new TermTokenizer(s);

        Expression acc = null;
        Expression left = null;
        Operator op = null;
        while (terms.hasMoreTerms()) {
            final String term = terms.nextTerm();

            if (left == null && acc == null) {
                left = createExpression(term);
            } else if (op == null) {
                op = new Operator(term);
            } else {
                Expression right = createExpression(term);

                acc = new CausalExpression(acc != null ? acc : left, op, right);

                left = null;
                op = null;
            }
        }

        return acc != null ? acc : left;
    }

    private Expression createExpression(final String term) {
        if (term.matches("^-?\\d+$")) {
            return new ValueExpression(term);
        } else if (term.matches("^[A-Za-z_]\\w*$")) {
            return new VariableExpression(term);
        } else if (term.contains(" ")) {
            return parse(term);
        } else {
            throw new IllegalStateException("well this is embarrassing");
        }
    }
}
