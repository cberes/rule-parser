package com.cberes.rule_parser;

public class App {
    public static void main(String[] args) {
        final RuleParserBig ruleParser = new RuleParserBig();

        for (int i = 1; i < args.length; i += 2) {
            ruleParser.addVariable(args[i], Integer.parseInt(args[i + 1]));
        }

        final int result = ruleParser.evaluate(args[0]);

        System.out.println(result);
    }
}
