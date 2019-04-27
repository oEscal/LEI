import java.util.List;
import java.util.ArrayList;

public class MyCalculatorVisitor extends CalculatorBaseVisitor<Object> {

   private List<String> sufix = new ArrayList<>();
   private int number_index = 0;

   @Override 
   public Object visitNumber(CalculatorParser.NumberContext ctx) { 
      
      sufix.add(number_index++, ctx.INT().toString());

      return visitChildren(ctx); 
   }

   @Override 
   public Object visitOp(CalculatorParser.OpContext ctx) {

      sufix.add(number_index, ctx.OPERATORS().toString());

      return visitChildren(ctx); 
   }

   @Override 
   public Object visitClose_par(CalculatorParser.Close_parContext ctx) { 

      number_index = sufix.size();

      return visitChildren(ctx); 
   }

   public String getResult(){
      
      String result = "";

      for (String s : sufix)
         result += s + " ";

      return result;
   }

}