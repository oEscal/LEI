grammar Calculadora;

program: stat* EOF;

stat: operation NEWLINE | var_define NEWLINE | NEWLINE;

operation: set '+' set | 
            set '\\' set |
            set '&' set |
            '(' operation ')';
var_define: VARRIABLE '=' set;

set: '{' (value ',')? value'}' |  VARRIABLE;
value: WORD+;

// lex
COMMENT: '--' .*? '\n' -> skip;
WORD: [+/]?[0-9]+;
VARRIABLE: [A-Z]+;
NEWLINE: [\r\n];
WS: [ \t] -> skip;
