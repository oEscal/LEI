import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class MyQuestionsVisitor extends QuestionsBaseVisitor<Object> {

   private Map<String, List<QandA>> questions = new HashMap<>(); 
   private String current_question;

   @Override 
   public Object visitId(QuestionsParser.IdContext ctx) {
      
      questions.put(current_question = ctx.WORD().toString(), new ArrayList<>());
      
      return visitChildren(ctx); 
   }

   @Override 
   public Object visitQuestion(QuestionsParser.QuestionContext ctx) { 

      String answer = "";

      for (TerminalNode word : ctx.WORD())
      answer += word.getText() + " ";

      questions
               .get(current_question)
               .add(new Question(answer));
   
      return visitChildren(ctx); 
   }

   @Override 
   public Object visitAnswer(QuestionsParser.AnswerContext ctx) { 

      String answer = "";

      for (TerminalNode word : ctx.WORD())
      answer += word.getText() + " ";

      questions
               .get(current_question)
               .add(new Answer(Integer.parseInt(ctx.NUMBER().getText()), answer));
   
      return visitChildren(ctx); 
   }

   public Map<String, List<QandA>> getQuestions() {

      return this.questions;
   }
}