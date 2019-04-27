// Generated from Questions.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuestionsParser}.
 */
public interface QuestionsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(QuestionsParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(QuestionsParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(QuestionsParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(QuestionsParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(QuestionsParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(QuestionsParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QuestionsParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QuestionsParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(QuestionsParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(QuestionsParser.AnswerContext ctx);
}