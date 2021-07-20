// Generated from /Users/kristofferpaulsson/IdeaProjects/angelon/src/org/angelon/Angelon.g4 by ANTLR 4.9.1
package org.angelon.checker;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AngelonParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, Enum=4, Class=5, Tuple=6, Data=7, Int=8, Float=9, 
		Bool=10, Set=11, List=12, Dict=13, Newline=14, DecimalInteger=15, OctalInteger=16, 
		HexadecimalInteger=17, BinaryInteger=18, DecimalFloat=19, ExponentFloat=20, 
		ImaginaryNumber=21, Boolean=22, None=23, StringArray=24, BytesArray=25, 
		INDENT=26, DEDENT=27;
	public static final int
		RULE_type = 0, RULE_const = 1, RULE_numberConst = 2, RULE_intConst = 3, 
		RULE_floatConst = 4, RULE_imaginaryConst = 5, RULE_boolConst = 6, RULE_noneConst = 7, 
		RULE_strConst = 8, RULE_bytesConst = 9, RULE_regexConst = 10, RULE_formatConst = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"type", "const", "numberConst", "intConst", "floatConst", "imaginaryConst", 
			"boolConst", "noneConst", "strConst", "bytesConst", "regexConst", "formatConst"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'b'", "'r'", "'f'", "'enum'", "'class'", "'tuple'", "'data'", 
			"'int'", "'float'", "'bool'", "'set'", "'list'", "'dict'", null, null, 
			null, null, null, null, null, null, null, "'None'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "Enum", "Class", "Tuple", "Data", "Int", "Float", 
			"Bool", "Set", "List", "Dict", "Newline", "DecimalInteger", "OctalInteger", 
			"HexadecimalInteger", "BinaryInteger", "DecimalFloat", "ExponentFloat", 
			"ImaginaryNumber", "Boolean", "None", "StringArray", "BytesArray", "INDENT", 
			"DEDENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Angelon.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AngelonParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Enum() { return getToken(AngelonParser.Enum, 0); }
		public TerminalNode Class() { return getToken(AngelonParser.Class, 0); }
		public TerminalNode Tuple() { return getToken(AngelonParser.Tuple, 0); }
		public TerminalNode Data() { return getToken(AngelonParser.Data, 0); }
		public TerminalNode Int() { return getToken(AngelonParser.Int, 0); }
		public TerminalNode Float() { return getToken(AngelonParser.Float, 0); }
		public TerminalNode Bool() { return getToken(AngelonParser.Bool, 0); }
		public TerminalNode Set() { return getToken(AngelonParser.Set, 0); }
		public TerminalNode List() { return getToken(AngelonParser.List, 0); }
		public TerminalNode Dict() { return getToken(AngelonParser.Dict, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Enum) | (1L << Class) | (1L << Tuple) | (1L << Data) | (1L << Int) | (1L << Float) | (1L << Bool) | (1L << Set) | (1L << List) | (1L << Dict))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstContext extends ParserRuleContext {
		public NumberConstContext numberConst() {
			return getRuleContext(NumberConstContext.class,0);
		}
		public BoolConstContext boolConst() {
			return getRuleContext(BoolConstContext.class,0);
		}
		public NoneConstContext noneConst() {
			return getRuleContext(NoneConstContext.class,0);
		}
		public StrConstContext strConst() {
			return getRuleContext(StrConstContext.class,0);
		}
		public BytesConstContext bytesConst() {
			return getRuleContext(BytesConstContext.class,0);
		}
		public RegexConstContext regexConst() {
			return getRuleContext(RegexConstContext.class,0);
		}
		public FormatConstContext formatConst() {
			return getRuleContext(FormatConstContext.class,0);
		}
		public ConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstContext const() throws RecognitionException {
		ConstContext _localctx = new ConstContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_const);
		try {
			setState(33);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DecimalInteger:
			case OctalInteger:
			case HexadecimalInteger:
			case BinaryInteger:
			case DecimalFloat:
			case ExponentFloat:
			case ImaginaryNumber:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				numberConst();
				}
				break;
			case Boolean:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				boolConst();
				}
				break;
			case None:
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				noneConst();
				}
				break;
			case StringArray:
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				strConst();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				bytesConst();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 6);
				{
				setState(31);
				regexConst();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 7);
				{
				setState(32);
				formatConst();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberConstContext extends ParserRuleContext {
		public IntConstContext intConst() {
			return getRuleContext(IntConstContext.class,0);
		}
		public FloatConstContext floatConst() {
			return getRuleContext(FloatConstContext.class,0);
		}
		public ImaginaryConstContext imaginaryConst() {
			return getRuleContext(ImaginaryConstContext.class,0);
		}
		public NumberConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterNumberConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitNumberConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitNumberConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberConstContext numberConst() throws RecognitionException {
		NumberConstContext _localctx = new NumberConstContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_numberConst);
		try {
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DecimalInteger:
			case OctalInteger:
			case HexadecimalInteger:
			case BinaryInteger:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				intConst();
				}
				break;
			case DecimalFloat:
			case ExponentFloat:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				floatConst();
				}
				break;
			case ImaginaryNumber:
				enterOuterAlt(_localctx, 3);
				{
				setState(37);
				imaginaryConst();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntConstContext extends ParserRuleContext {
		public TerminalNode DecimalInteger() { return getToken(AngelonParser.DecimalInteger, 0); }
		public TerminalNode OctalInteger() { return getToken(AngelonParser.OctalInteger, 0); }
		public TerminalNode HexadecimalInteger() { return getToken(AngelonParser.HexadecimalInteger, 0); }
		public TerminalNode BinaryInteger() { return getToken(AngelonParser.BinaryInteger, 0); }
		public IntConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterIntConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitIntConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitIntConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntConstContext intConst() throws RecognitionException {
		IntConstContext _localctx = new IntConstContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_intConst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DecimalInteger) | (1L << OctalInteger) | (1L << HexadecimalInteger) | (1L << BinaryInteger))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatConstContext extends ParserRuleContext {
		public TerminalNode DecimalFloat() { return getToken(AngelonParser.DecimalFloat, 0); }
		public TerminalNode ExponentFloat() { return getToken(AngelonParser.ExponentFloat, 0); }
		public FloatConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterFloatConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitFloatConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitFloatConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatConstContext floatConst() throws RecognitionException {
		FloatConstContext _localctx = new FloatConstContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_floatConst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_la = _input.LA(1);
			if ( !(_la==DecimalFloat || _la==ExponentFloat) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImaginaryConstContext extends ParserRuleContext {
		public TerminalNode ImaginaryNumber() { return getToken(AngelonParser.ImaginaryNumber, 0); }
		public ImaginaryConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imaginaryConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterImaginaryConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitImaginaryConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitImaginaryConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImaginaryConstContext imaginaryConst() throws RecognitionException {
		ImaginaryConstContext _localctx = new ImaginaryConstContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_imaginaryConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(ImaginaryNumber);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolConstContext extends ParserRuleContext {
		public TerminalNode Boolean() { return getToken(AngelonParser.Boolean, 0); }
		public BoolConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterBoolConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitBoolConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitBoolConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolConstContext boolConst() throws RecognitionException {
		BoolConstContext _localctx = new BoolConstContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_boolConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(Boolean);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoneConstContext extends ParserRuleContext {
		public TerminalNode None() { return getToken(AngelonParser.None, 0); }
		public NoneConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noneConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterNoneConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitNoneConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitNoneConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoneConstContext noneConst() throws RecognitionException {
		NoneConstContext _localctx = new NoneConstContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_noneConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(None);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrConstContext extends ParserRuleContext {
		public TerminalNode StringArray() { return getToken(AngelonParser.StringArray, 0); }
		public StrConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterStrConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitStrConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitStrConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrConstContext strConst() throws RecognitionException {
		StrConstContext _localctx = new StrConstContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_strConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(StringArray);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytesConstContext extends ParserRuleContext {
		public TerminalNode BytesArray() { return getToken(AngelonParser.BytesArray, 0); }
		public BytesConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytesConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterBytesConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitBytesConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitBytesConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytesConstContext bytesConst() throws RecognitionException {
		BytesConstContext _localctx = new BytesConstContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bytesConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__0);
			setState(53);
			match(BytesArray);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegexConstContext extends ParserRuleContext {
		public TerminalNode StringArray() { return getToken(AngelonParser.StringArray, 0); }
		public RegexConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterRegexConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitRegexConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitRegexConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexConstContext regexConst() throws RecognitionException {
		RegexConstContext _localctx = new RegexConstContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_regexConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__1);
			setState(56);
			match(StringArray);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormatConstContext extends ParserRuleContext {
		public TerminalNode StringArray() { return getToken(AngelonParser.StringArray, 0); }
		public FormatConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).enterFormatConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AngelonListener ) ((AngelonListener)listener).exitFormatConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AngelonVisitor ) return ((AngelonVisitor<? extends T>)visitor).visitFormatConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormatConstContext formatConst() throws RecognitionException {
		FormatConstContext _localctx = new FormatConstContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_formatConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__2);
			setState(59);
			match(StringArray);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35@\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\4\3\4\3"+
		"\4\5\4)\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\2\5\3\2\6\17\3\2\21\24\3\2\25\26\2;\2\32\3\2\2\2\4#\3\2\2\2\6(\3\2\2"+
		"\2\b*\3\2\2\2\n,\3\2\2\2\f.\3\2\2\2\16\60\3\2\2\2\20\62\3\2\2\2\22\64"+
		"\3\2\2\2\24\66\3\2\2\2\269\3\2\2\2\30<\3\2\2\2\32\33\t\2\2\2\33\3\3\2"+
		"\2\2\34$\5\6\4\2\35$\5\16\b\2\36$\5\20\t\2\37$\5\22\n\2 $\5\24\13\2!$"+
		"\5\26\f\2\"$\5\30\r\2#\34\3\2\2\2#\35\3\2\2\2#\36\3\2\2\2#\37\3\2\2\2"+
		"# \3\2\2\2#!\3\2\2\2#\"\3\2\2\2$\5\3\2\2\2%)\5\b\5\2&)\5\n\6\2\')\5\f"+
		"\7\2(%\3\2\2\2(&\3\2\2\2(\'\3\2\2\2)\7\3\2\2\2*+\t\3\2\2+\t\3\2\2\2,-"+
		"\t\4\2\2-\13\3\2\2\2./\7\27\2\2/\r\3\2\2\2\60\61\7\30\2\2\61\17\3\2\2"+
		"\2\62\63\7\31\2\2\63\21\3\2\2\2\64\65\7\32\2\2\65\23\3\2\2\2\66\67\7\3"+
		"\2\2\678\7\33\2\28\25\3\2\2\29:\7\4\2\2:;\7\32\2\2;\27\3\2\2\2<=\7\5\2"+
		"\2=>\7\32\2\2>\31\3\2\2\2\4#(";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}