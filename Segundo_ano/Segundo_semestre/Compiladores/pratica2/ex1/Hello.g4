grammar Hello;

greetings : (hello | bye)+ EOF;   // recursive call

hello : 'hello' {
   System.out.print("Olá ");
} (ID {
   System.out.print($ID.text + " ");
})+ {
   System.out.println();
};

bye : 'bye' {
   System.out.print("Bá xau ");
} (ID {
   System.out.print($ID.text + " ");
})+ {
   System.out.println();
};

ID : [a-zA-Z]+;
WS : [ \t\r\n]+ -> skip;