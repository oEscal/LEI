grammar Calculator;

@header {
   import java.util.HashMap;
}

@members {
   HashMap<String, Integer> variables = new HashMap<>();
}

program: stat* EOF;

stat: expr {
   System.out.println("Result: " + $expr.result);
} NEWLINE | assignment | NEWLINE;

expr returns [int result=0]: e1 = expr op = ('*' | '/') e2 = expr {
         switch($op.text){ 
            case "*": $result = $e1.result * $e2.result; break;
            case "/": $result = $e1.result / $e2.result; break;
            default:  System.err.println("Calc error!");
         }
      } |
      e1 = expr op = ('+' | '-') e2 = expr {
         switch($op.text){ 
            case "+": $result = $e1.result + $e2.result; break;
            case "-": $result = $e1.result - $e2.result; break;
            default: System.err.println("Calc error!");
         }
      } |
      INT {
         $result = Integer.parseInt($INT.text);
      } | 
      ID {
         $result = variables.get($ID.text);
      } |
      '(' expr ')' {
         $result = $expr.result; 
      };

assignment: ID '=' expr NEWLINE {
   String var_name = $ID.text;

   variables.put(var_name, $expr.result);
};

ID: [a-zA-Z_]+ ;
INT: [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;