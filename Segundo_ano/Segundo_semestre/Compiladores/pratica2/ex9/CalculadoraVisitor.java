// Generated from Calculadora.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalculadoraParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalculadoraVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalculadoraParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(CalculadoraParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(CalculadoraParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#var_define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_define(CalculadoraParser.Var_defineContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(CalculadoraParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(CalculadoraParser.ValueContext ctx);
}