

public class MyJavaListener extends Java8BaseListener {

   @Override 
   public void enterClassType(Java8Parser.ClassTypeContext ctx) {
      System.out.println("Interface or extended class: " + ctx.getText());
   }

   @Override 
   public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) { 
      System.out.println("Method: " + ctx.methodHeader().methodDeclarator().Identifier());
   }
}