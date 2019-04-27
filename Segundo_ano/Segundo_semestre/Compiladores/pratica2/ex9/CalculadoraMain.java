import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class CalculadoraMain {
   public static void main(String[] args) throws Exception {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      CalculadoraLexer lexer = new CalculadoraLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      CalculadoraParser parser = new CalculadoraParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at program rule:
      ParseTree tree = parser.program();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         MyCalculadoraVisitor visitor0 = new MyCalculadoraVisitor();
         visitor0.visit(tree);
      }
   }
}
