grammar Calculator;

program: stat* EOF;

stat: number=INT ' - ' name=NAME NEWLINE | NEWLINE;

INT: [0-9]+;
NAME: [a-zA-Z]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;