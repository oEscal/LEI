// Generated from Questions.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QuestionsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QuestionsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QuestionsParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(QuestionsParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionsParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(QuestionsParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionsParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(QuestionsParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionsParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QuestionsParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionsParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(QuestionsParser.AnswerContext ctx);
}