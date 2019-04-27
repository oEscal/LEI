// Generated from Calculadora.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculadoraParser}.
 */
public interface CalculadoraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalculadoraParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalculadoraParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CalculadoraParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CalculadoraParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(CalculadoraParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(CalculadoraParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#var_define}.
	 * @param ctx the parse tree
	 */
	void enterVar_define(CalculadoraParser.Var_defineContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#var_define}.
	 * @param ctx the parse tree
	 */
	void exitVar_define(CalculadoraParser.Var_defineContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(CalculadoraParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(CalculadoraParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(CalculadoraParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(CalculadoraParser.ValueContext ctx);
}