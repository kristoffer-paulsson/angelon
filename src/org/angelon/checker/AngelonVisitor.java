// Generated from /Users/kristofferpaulsson/IdeaProjects/angelon/src/org/angelon/Angelon.g4 by ANTLR 4.9.1
package org.angelon.checker;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AngelonParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AngelonVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AngelonParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(AngelonParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#const}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(AngelonParser.ConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#numberConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberConst(AngelonParser.NumberConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#intConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntConst(AngelonParser.IntConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#floatConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatConst(AngelonParser.FloatConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#imaginaryConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImaginaryConst(AngelonParser.ImaginaryConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#boolConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolConst(AngelonParser.BoolConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#noneConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoneConst(AngelonParser.NoneConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#strConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrConst(AngelonParser.StrConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#bytesConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytesConst(AngelonParser.BytesConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#regexConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexConst(AngelonParser.RegexConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link AngelonParser#formatConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatConst(AngelonParser.FormatConstContext ctx);
}