grammar Calculator;

program: stat* EOF;

stat: expr {
   System.out.println("Result: " + $expr.result);
} NEWLINE | NEWLINE;

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
      '(' expr ')' {
         $result = $expr.result; 
      };

INT: [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;