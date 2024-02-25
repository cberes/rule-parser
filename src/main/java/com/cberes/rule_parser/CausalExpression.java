package com.cberes.rule_parser;

class CausalExpression implements Expression {
    private final Expression left;
    private final Operator op;
    private final Expression right;

    CausalExpression(final Expression left, final Operator op, final Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public int evaluateWith(final SymbolTable symbols) {
        return op.apply(left.evaluateWith(symbols), right.evaluateWith(symbols));
    }
}
