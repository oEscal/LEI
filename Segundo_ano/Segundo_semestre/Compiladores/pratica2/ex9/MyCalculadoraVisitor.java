import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.List;

public class MyCalculadoraVisitor extends CalculadoraBaseVisitor<Object> {

   private List<List<String>> sets;

   public MyCalculadoraVisitor () {
      this.sets = new ArrayList();
   }

   @Override 
   public Object visitOperation(CalculadoraParser.OperationContext ctx) { 

      ctx.set().forEach(operation -> {

         this.sets.add(new ArrayList<>());

         operation.value().forEach(value -> {
            this.sets.get(this.sets.size() - 1).add(parseNumbersToNumber(value.WORD()));
         });
      });

      return visitChildren(ctx); 
   }

   private String parseNumbersToNumber (List<TerminalNode> numbers) {

      String result = "";
      for (TerminalNode number : numbers)
         result += number.toString();

      return result;
   }

}