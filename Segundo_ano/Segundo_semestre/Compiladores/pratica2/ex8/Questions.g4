grammar Questions;

program: stat* EOF;

stat: id '(' '"' question '"' ')' '{'answer*'}';

id: WORD;
question: WORD*;
answer: '"' WORD* '"' ':' NUMBER ';';

// lex
COMMENT: '#' .*? '\n' -> skip;
NUMBER: [0-9]+;
WORD: [A-Za-z0-9.]+;
WS: [ \t\r\n] -> skip;