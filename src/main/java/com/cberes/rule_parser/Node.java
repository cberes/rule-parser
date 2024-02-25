package com.cberes.rule_parser;

class Node {

    private final String text;

    public Node(final String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public boolean isLiteral() {
        return text.matches("^-?[0-9A-Za-z]+$");
    }
}
