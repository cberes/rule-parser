package com.cberes.rule_parser;

import java.util.Iterator;
import java.util.Optional;

public class TermTokenizer implements Iterator<String> {
    private final String current;
    private int currentPosition;

    public TermTokenizer(final String current) {
        this.current = current;
        this.currentPosition = 0;
    }

    @Override
    public String next() {
        return nextTerm();
    }

    public String nextTerm() {
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

    @Override
    public boolean hasNext() {
        return hasMoreTerms();
    }

    public boolean hasMoreTerms() {
        return current != null && currentPosition < current.length();
    }
}
