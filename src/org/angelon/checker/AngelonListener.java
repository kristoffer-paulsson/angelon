// Generated from /Users/kristofferpaulsson/IdeaProjects/angelon/src/org/angelon/Angelon.g4 by ANTLR 4.9.1
package org.angelon.checker;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AngelonParser}.
 */
public interface AngelonListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AngelonParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(AngelonParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(AngelonParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#const}.
	 * @param ctx the parse tree
	 */
	void enterConst(AngelonParser.ConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#const}.
	 * @param ctx the parse tree
	 */
	void exitConst(AngelonParser.ConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#numberConst}.
	 * @param ctx the parse tree
	 */
	void enterNumberConst(AngelonParser.NumberConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#numberConst}.
	 * @param ctx the parse tree
	 */
	void exitNumberConst(AngelonParser.NumberConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#intConst}.
	 * @param ctx the parse tree
	 */
	void enterIntConst(AngelonParser.IntConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#intConst}.
	 * @param ctx the parse tree
	 */
	void exitIntConst(AngelonParser.IntConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#floatConst}.
	 * @param ctx the parse tree
	 */
	void enterFloatConst(AngelonParser.FloatConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#floatConst}.
	 * @param ctx the parse tree
	 */
	void exitFloatConst(AngelonParser.FloatConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#imaginaryConst}.
	 * @param ctx the parse tree
	 */
	void enterImaginaryConst(AngelonParser.ImaginaryConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#imaginaryConst}.
	 * @param ctx the parse tree
	 */
	void exitImaginaryConst(AngelonParser.ImaginaryConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#boolConst}.
	 * @param ctx the parse tree
	 */
	void enterBoolConst(AngelonParser.BoolConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#boolConst}.
	 * @param ctx the parse tree
	 */
	void exitBoolConst(AngelonParser.BoolConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#noneConst}.
	 * @param ctx the parse tree
	 */
	void enterNoneConst(AngelonParser.NoneConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#noneConst}.
	 * @param ctx the parse tree
	 */
	void exitNoneConst(AngelonParser.NoneConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#strConst}.
	 * @param ctx the parse tree
	 */
	void enterStrConst(AngelonParser.StrConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#strConst}.
	 * @param ctx the parse tree
	 */
	void exitStrConst(AngelonParser.StrConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#bytesConst}.
	 * @param ctx the parse tree
	 */
	void enterBytesConst(AngelonParser.BytesConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#bytesConst}.
	 * @param ctx the parse tree
	 */
	void exitBytesConst(AngelonParser.BytesConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#regexConst}.
	 * @param ctx the parse tree
	 */
	void enterRegexConst(AngelonParser.RegexConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#regexConst}.
	 * @param ctx the parse tree
	 */
	void exitRegexConst(AngelonParser.RegexConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link AngelonParser#formatConst}.
	 * @param ctx the parse tree
	 */
	void enterFormatConst(AngelonParser.FormatConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link AngelonParser#formatConst}.
	 * @param ctx the parse tree
	 */
	void exitFormatConst(AngelonParser.FormatConstContext ctx);
}