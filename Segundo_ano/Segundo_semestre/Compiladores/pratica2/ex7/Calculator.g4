grammar Calculator;

program: stat* EOF;

stat: expr NEWLINE | NEWLINE;

expr: expr op expr |
      number | 
      '(' expr close_par;

number: INT;
op: OPERATORS;
close_par: ')';

OPERATORS: [*/+-];
INT: [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;