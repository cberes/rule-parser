# rule-parser

I wrote this in 2019 while reading _Working Effectively with Legacy Code_ by Michael C Feathers. The `RuleParser` example is from Chapter 20: "This Class Is Too Big and I Don't Want It to Get Any Bigger."

In this chapter Feathers explains the Single-Responsibility Principle. First he describes a class, RuleParser, with too many responsibilities. In my code, I've named it `RuleParserBig`. He then shows an alternative with separate responsibilities. These are `RuleEvaluator`, `RuleParser`, `SymbolTable`, `Expression`, and `TermTokenizer`.
