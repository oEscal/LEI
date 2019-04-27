grammar SuffixCalculator;

program: stat* EOF;

stat: expr{
   System.out.println("Result: " + $expr.result);
} NEWLINE | NEWLINE;

expr returns[double result=0]: e1=expr e2=expr Op {
   switch($Op.text){ 
      case "*" : $result = $e1.result * $e2.result; break;
      case "/" : $result = $e1.result / $e2.result; break;
      case "+" : $result = $e1.result + $e2.result; break;
      case "-" : $result = $e1.result - $e2.result; break;
      default: System.err.println("Calc error!");
   }
} | Number {
   $result = Double.parseDouble($Number.text);
};

Op: [*/+-]; // ou -> '*' | '/' | '+' | '-'
Number: [0-9]+('.'[0-9]+)?;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;