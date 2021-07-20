// Generated from /Users/kristofferpaulsson/IdeaProjects/angelon/src/org/angelon/Angelon.g4 by ANTLR 4.9.1
package org.angelon.checker;

// Taken from: https://github.com/antlr/grammars-v4/blob/master/python/tiny-python/tiny-grammar-with-actions/Python3.g4
import org.antlr.v4.runtime.misc.Interval;
import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AngelonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, Enum=4, Class=5, Tuple=6, Data=7, Int=8, Float=9, 
		Bool=10, Set=11, List=12, Dict=13, Newline=14, DecimalInteger=15, OctalInteger=16, 
		HexadecimalInteger=17, BinaryInteger=18, DecimalFloat=19, ExponentFloat=20, 
		ImaginaryNumber=21, Boolean=22, None=23, StringArray=24, BytesArray=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "Enum", "Class", "Tuple", "Data", "Int", "Float", 
			"Bool", "Set", "List", "Dict", "Newline", "Symbol", "Spaces", "Comment", 
			"Joining", "DecimalInteger", "OctalInteger", "HexadecimalInteger", "BinaryInteger", 
			"DecimalFloat", "ExponentFloat", "ImaginaryNumber", "Boolean", "None", 
			"StringArray", "BytesArray"
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
			"ImaginaryNumber", "Boolean", "None", "StringArray", "BytesArray"
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


	// Taken from: https://github.com/antlr/grammars-v4/blob/master/python/tiny-python/tiny-grammar-with-actions/Python3.g4

	private Stack<Integer> indentLengths = new Stack<>();
	private LinkedList<Token> pendingTokens = new LinkedList<>();
	private int lastPendingTokenType;
	private int opened = 0;
	private boolean wasSpaceIndentation = false;
	private boolean wasTabIndentation = false;
	private List<String> warnings = new ArrayList<>();
	private List<String> errors = new ArrayList<>();
	public static final String TEXT_LEXER = "lexer --> ";
	public static final String TEXT_INSERTED_INDENT = "inserted INDENT";

	@Override
	public Token nextToken() {
	    if (_input.size() == 0) {
	        return new CommonToken(EOF, "<EOF>"); // processing of the input stream until the first returning EOF
	    } else {
	        checkNextToken();
	        return this.pendingTokens.pollFirst(); // append the token stream with the upcoming pending token
	    }
	}

	private void checkNextToken() {
	    if (this.indentLengths != null) { // after the first incoming EOF token the indentLengths stack will be set to null
	        final int startSize = this.pendingTokens.size();
	        Token curToken;
	        do {
	            curToken = super.nextToken(); // get the next token from the input stream
	            checkStartOfInput(curToken);
	            switch (curToken.getType()) {
	                case OPEN_PAREN:
	                case OPEN_BRACK:
	                case OPEN_BRACE:
	                    this.opened++;
	                    this.pendingTokens.addLast(curToken);
	                    break;
	                case CLOSE_PAREN:
	                case CLOSE_BRACK:
	                case CLOSE_BRACE:
	                    this.opened--;
	                    this.pendingTokens.addLast(curToken);
	                    break;
	                case NEWLINE:
	                    handleNewLineToken(curToken);
	                    break;
	                case EOF:
	                    handleEofToken(curToken); // indentLengths stack will be set to null
	                    break;
	                default:
	                    this.pendingTokens.addLast(curToken); // insert the current token
	            }
	        } while (this.pendingTokens.size() == startSize);
	        this.lastPendingTokenType = curToken.getType();
	    }
	}

	private void checkStartOfInput(Token curToken) {
	    if (indentLengths.size() == 0) { // We're at the first token
	        indentLengths.push(0);  // initialize the stack with default 0 indentation length
	        if (_input.getText(new Interval(0, 0)).trim().length() == 0) { // the first char of the input is a whitespace
	            this.insertLeadingTokens(curToken.getType(), curToken.getStartIndex());
	        }
	    }
	}

	private void handleNewLineToken(Token curToken) {
	    if (this.opened == 0) { //*** https://docs.python.org/3/reference/lexical_analysis.html#implicit-line-joining
	        switch (_input.LA(1) /* next symbol */) { //*** https://www.antlr.org/api/Java/org/antlr/v4/runtime/IntStream.html#LA(int)
	            case '\r':
	            case '\n':
	            case '\f':
	            case '#':   //*** https://docs.python.org/3/reference/lexical_analysis.html#blank-lines
	            case EOF:   // skip the trailing inconsistent dedent or the trailing unexpected indent (or the trailing indent)
	                return; // We're on a blank line or before a comment or before the EOF, skip the NEWLINE token
	            default:
	                this.pendingTokens.addLast(curToken); // insert the current NEWLINE token
	                this.insertIndentDedentTokens(this.getIndentationLength(curToken.getText())); //*** https://docs.python.org/3/reference/lexical_analysis.html#indentation
	        }
	    }
	}

	private void handleEofToken(Token curToken) {
	    this.insertTrailingTokens(this.lastPendingTokenType); // indentLengths stack will be null!
	    this.pendingTokens.addLast(curToken); // insert the current EOF token
	    this.checkSpaceAndTabIndentation();
	}

	private void insertLeadingTokens(int type, int startIndex) {
	    if (type != NEWLINE && type != EOF) { // (after a whitespace) The first token is visible, so We insert a NEWLINE and an INDENT token before it to raise an 'unexpected indent' error later by the parser
	        this.insertToken(0, startIndex - 1, "<inserted leading NEWLINE>" + " ".repeat(startIndex), NEWLINE, 1, 0);
	        this.insertToken(startIndex, startIndex - 1, "<" + TEXT_INSERTED_INDENT + ", " + this.getIndentationDescription(startIndex) + ">", Python3Parser.INDENT, 1, startIndex);
	        this.indentLengths.push(startIndex);
	    }
	}

	private void insertIndentDedentTokens(int curIndentLength) {
	    int prevIndentLength = this.indentLengths.peek();
	    if (curIndentLength > prevIndentLength) { // insert an INDENT token
	        this.insertToken("<" + TEXT_INSERTED_INDENT + ", " + this.getIndentationDescription(curIndentLength) + ">", Python3Parser.INDENT);
	        this.indentLengths.push(curIndentLength);
	    } else {
	        while (curIndentLength < prevIndentLength) {   // More than 1 DEDENT token may be inserted
	            this.indentLengths.pop();
	            prevIndentLength = this.indentLengths.peek();
	            if (curIndentLength <= prevIndentLength) {
	                this.insertToken("<inserted DEDENT, " + this.getIndentationDescription(prevIndentLength) + ">", Python3Parser.DEDENT);
	            } else {
	                this.insertToken("<inserted inconsistent DEDENT, " + "length=" + curIndentLength + ">", Python3Parser.DEDENT);
	                this.errors.add(TEXT_LEXER + "line " + getLine() + ":" + getCharPositionInLine() + "\t IndentationError: unindent does not match any outer indentation level");
	            }
	        }
	    }
	}

	private void insertTrailingTokens(int type) {
	    if (type != NEWLINE && type != Python3Parser.DEDENT) { // If the last pending token was not a NEWLINE and not a DEDENT then
	        this.insertToken("<inserted trailing NEWLINE>", NEWLINE); // insert an extra trailing NEWLINE token that serves as the end of the statement
	    }

	    while (this.indentLengths.size() > 1) { // Now insert as much trailing DEDENT tokens as needed
	        this.insertToken("<inserted trailing DEDENT, " + this.getIndentationDescription(this.indentLengths.pop()) + ">", Python3Parser.DEDENT);
	    }
	    this.indentLengths = null; // there will be no more token read from the input stream
	}

	private String getIndentationDescription(int lengthOfIndent) {
	    return "length=" + lengthOfIndent + ", level=" + this.indentLengths.size();
	}

	private void insertToken(String text, int type) {
	    final int startIndex = _tokenStartCharIndex + getText().length(); //*** https://www.antlr.org/api/Java/org/antlr/v4/runtime/Lexer.html#_tokenStartCharIndex
	    this.insertToken(startIndex, startIndex - 1, text, type, getLine(), getCharPositionInLine());
	}

	private void insertToken(int startIndex, int stopIndex, String text, int type, int line, int charPositionInLine) {
	    CommonToken token = new CommonToken(_tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, startIndex, stopIndex); //*** https://www.antlr.org/api/Java/org/antlr/v4/runtime/CommonToken.html
	    token.setText(text);
	    token.setLine(line);
	    token.setCharPositionInLine(charPositionInLine);
	    this.pendingTokens.addLast(token);
	}

	private int getIndentationLength(String textOfMatchedNEWLINE) {
	    int count = 0;
	    for (char ch : textOfMatchedNEWLINE.toCharArray()) {
	        switch (ch) {
	            case ' ': // A normal space char
	                this.wasSpaceIndentation = true;
	                count++;
	                break;
	            case '\t':
	                this.wasTabIndentation = true;
	                count += 8 - (count % 8);
	                break;
	        }
	    }
	    return count;
	}

	private void checkSpaceAndTabIndentation() {
	    if (this.wasSpaceIndentation && this.wasTabIndentation) {
	        this.warnings.add("Mixture of space and tab were used for indentation.");
	    }
	}

	public List<String> getWarnings() {
	    return this.warnings;
	}

	public List<String> getErrorMessages() {
	    return this.errors;
	}


	public AngelonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Angelon.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 13:
			Newline_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void Newline_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:


			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return Newline_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean Newline_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return self.atStartOfInput();
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u0179\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\5\17z\n\17\3\17\3\17\5\17~\n\17\3\17\5"+
		"\17\u0081\n\17\5\17\u0083\n\17\3\17\3\17\3\20\3\20\7\20\u0089\n\20\f\20"+
		"\16\20\u008c\13\20\3\21\6\21\u008f\n\21\r\21\16\21\u0090\3\22\3\22\7\22"+
		"\u0095\n\22\f\22\16\22\u0098\13\22\3\23\3\23\5\23\u009c\n\23\3\23\5\23"+
		"\u009f\n\23\3\23\3\23\5\23\u00a3\n\23\3\24\3\24\7\24\u00a7\n\24\f\24\16"+
		"\24\u00aa\13\24\3\24\6\24\u00ad\n\24\r\24\16\24\u00ae\5\24\u00b1\n\24"+
		"\3\25\3\25\3\25\3\25\6\25\u00b7\n\25\r\25\16\25\u00b8\3\26\3\26\3\26\3"+
		"\26\6\26\u00bf\n\26\r\26\16\26\u00c0\3\27\3\27\3\27\3\27\6\27\u00c7\n"+
		"\27\r\27\16\27\u00c8\3\30\6\30\u00cc\n\30\r\30\16\30\u00cd\3\30\3\30\6"+
		"\30\u00d2\n\30\r\30\16\30\u00d3\3\30\3\30\6\30\u00d8\n\30\r\30\16\30\u00d9"+
		"\5\30\u00dc\n\30\3\31\6\31\u00df\n\31\r\31\16\31\u00e0\3\31\5\31\u00e4"+
		"\n\31\3\31\3\31\5\31\u00e8\n\31\3\31\6\31\u00eb\n\31\r\31\16\31\u00ec"+
		"\3\32\3\32\3\32\6\32\u00f2\n\32\r\32\16\32\u00f3\5\32\u00f6\n\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0103\n\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35"+
		"\u0113\n\35\f\35\16\35\u0116\13\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\7\35\u0124\n\35\f\35\16\35\u0127\13\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u0132\n\35\f\35\16\35\u0135"+
		"\13\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u013e\n\35\f\35\16\35\u0141"+
		"\13\35\3\35\5\35\u0144\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u014d"+
		"\n\36\f\36\16\36\u0150\13\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\7\36\u015c\n\36\f\36\16\36\u015f\13\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\7\36\u0168\n\36\f\36\16\36\u016b\13\36\3\36\3\36\3\36\3"+
		"\36\3\36\7\36\u0172\n\36\f\36\16\36\u0175\13\36\3\36\5\36\u0178\n\36\7"+
		"\u00cd\u0114\u0125\u014e\u015d\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\2!\2#\2%\2\'\21)\22+\23-\24/\25"+
		"\61\26\63\27\65\30\67\319\32;\33\3\2\24\5\2C\\aac|\6\2\62;C\\aac|\4\2"+
		"\13\13\"\"\4\2\f\f\16\17\3\2\63;\3\2\62;\3\2\629\5\2\62;CHch\3\2\62\63"+
		"\4\2GGgg\4\2--//\3\2^^\6\2\f\f\16\17$$^^\6\2\f\f\16\17))^^\4\2\2]_\u0081"+
		"\3\2\2\u0081\7\2\2\13\r\16\20#%]_\u0081\7\2\2\13\r\16\20(*]_\u0081\2\u01aa"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5?\3\2\2\2\7A\3\2\2\2\tC\3\2"+
		"\2\2\13H\3\2\2\2\rN\3\2\2\2\17T\3\2\2\2\21Y\3\2\2\2\23]\3\2\2\2\25c\3"+
		"\2\2\2\27h\3\2\2\2\31l\3\2\2\2\33q\3\2\2\2\35\u0082\3\2\2\2\37\u0086\3"+
		"\2\2\2!\u008e\3\2\2\2#\u0092\3\2\2\2%\u0099\3\2\2\2\'\u00b0\3\2\2\2)\u00b2"+
		"\3\2\2\2+\u00ba\3\2\2\2-\u00c2\3\2\2\2/\u00db\3\2\2\2\61\u00e3\3\2\2\2"+
		"\63\u00f5\3\2\2\2\65\u0102\3\2\2\2\67\u0104\3\2\2\29\u0143\3\2\2\2;\u0177"+
		"\3\2\2\2=>\7d\2\2>\4\3\2\2\2?@\7t\2\2@\6\3\2\2\2AB\7h\2\2B\b\3\2\2\2C"+
		"D\7g\2\2DE\7p\2\2EF\7w\2\2FG\7o\2\2G\n\3\2\2\2HI\7e\2\2IJ\7n\2\2JK\7c"+
		"\2\2KL\7u\2\2LM\7u\2\2M\f\3\2\2\2NO\7v\2\2OP\7w\2\2PQ\7r\2\2QR\7n\2\2"+
		"RS\7g\2\2S\16\3\2\2\2TU\7f\2\2UV\7c\2\2VW\7v\2\2WX\7c\2\2X\20\3\2\2\2"+
		"YZ\7k\2\2Z[\7p\2\2[\\\7v\2\2\\\22\3\2\2\2]^\7h\2\2^_\7n\2\2_`\7q\2\2`"+
		"a\7c\2\2ab\7v\2\2b\24\3\2\2\2cd\7d\2\2de\7q\2\2ef\7q\2\2fg\7n\2\2g\26"+
		"\3\2\2\2hi\7u\2\2ij\7g\2\2jk\7v\2\2k\30\3\2\2\2lm\7n\2\2mn\7k\2\2no\7"+
		"u\2\2op\7v\2\2p\32\3\2\2\2qr\7f\2\2rs\7k\2\2st\7e\2\2tu\7v\2\2u\34\3\2"+
		"\2\2vw\6\17\2\2w\u0083\5!\21\2xz\7\17\2\2yx\3\2\2\2yz\3\2\2\2z{\3\2\2"+
		"\2{~\7\f\2\2|~\4\16\17\2}y\3\2\2\2}|\3\2\2\2~\u0080\3\2\2\2\177\u0081"+
		"\5!\21\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082"+
		"v\3\2\2\2\u0082}\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\17\2\2\u0085"+
		"\36\3\2\2\2\u0086\u008a\t\2\2\2\u0087\u0089\t\3\2\2\u0088\u0087\3\2\2"+
		"\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b "+
		"\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008f\t\4\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\"\3\2\2\2"+
		"\u0092\u0096\7%\2\2\u0093\u0095\n\5\2\2\u0094\u0093\3\2\2\2\u0095\u0098"+
		"\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097$\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0099\u009b\7^\2\2\u009a\u009c\5!\21\2\u009b\u009a\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u00a2\3\2\2\2\u009d\u009f\7\17\2\2\u009e"+
		"\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a3\7\f"+
		"\2\2\u00a1\u00a3\4\16\17\2\u00a2\u009e\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3"+
		"&\3\2\2\2\u00a4\u00a8\t\6\2\2\u00a5\u00a7\t\7\2\2\u00a6\u00a5\3\2\2\2"+
		"\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b1"+
		"\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ad\7\62\2\2\u00ac\u00ab\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1"+
		"\3\2\2\2\u00b0\u00a4\3\2\2\2\u00b0\u00ac\3\2\2\2\u00b1(\3\2\2\2\u00b2"+
		"\u00b3\7\62\2\2\u00b3\u00b4\7q\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b7\t\b"+
		"\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9*\3\2\2\2\u00ba\u00bb\7\62\2\2\u00bb\u00bc\7z\2\2"+
		"\u00bc\u00be\3\2\2\2\u00bd\u00bf\t\t\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1,\3\2\2\2\u00c2"+
		"\u00c3\7\62\2\2\u00c3\u00c4\7d\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c7\t\n"+
		"\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9.\3\2\2\2\u00ca\u00cc\t\7\2\2\u00cb\u00ca\3\2\2\2"+
		"\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00d1\7\60\2\2\u00d0\u00d2\t\7\2\2\u00d1\u00d0\3\2\2\2"+
		"\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00dc"+
		"\3\2\2\2\u00d5\u00d7\7\60\2\2\u00d6\u00d8\t\7\2\2\u00d7\u00d6\3\2\2\2"+
		"\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc"+
		"\3\2\2\2\u00db\u00cb\3\2\2\2\u00db\u00d5\3\2\2\2\u00dc\60\3\2\2\2\u00dd"+
		"\u00df\t\7\2\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e4\5/\30\2\u00e3"+
		"\u00de\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\t\13"+
		"\2\2\u00e6\u00e8\t\f\2\2\u00e7\u00e6\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00ea\3\2\2\2\u00e9\u00eb\t\7\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\62\3\2\2\2\u00ee\u00f6"+
		"\5/\30\2\u00ef\u00f6\5\61\31\2\u00f0\u00f2\t\7\2\2\u00f1\u00f0\3\2\2\2"+
		"\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f6"+
		"\3\2\2\2\u00f5\u00ee\3\2\2\2\u00f5\u00ef\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00f8\7l\2\2\u00f8\64\3\2\2\2\u00f9\u00fa\7V\2\2"+
		"\u00fa\u00fb\7t\2\2\u00fb\u00fc\7w\2\2\u00fc\u0103\7g\2\2\u00fd\u00fe"+
		"\7H\2\2\u00fe\u00ff\7c\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7u\2\2\u0101"+
		"\u0103\7g\2\2\u0102\u00f9\3\2\2\2\u0102\u00fd\3\2\2\2\u0103\66\3\2\2\2"+
		"\u0104\u0105\7P\2\2\u0105\u0106\7q\2\2\u0106\u0107\7p\2\2\u0107\u0108"+
		"\7g\2\2\u01088\3\2\2\2\u0109\u010a\7$\2\2\u010a\u010b\7$\2\2\u010b\u010c"+
		"\7$\2\2\u010c\u0114\3\2\2\2\u010d\u0113\n\r\2\2\u010e\u010f\7^\2\2\u010f"+
		"\u0113\13\2\2\2\u0110\u0111\7^\2\2\u0111\u0113\5\35\17\2\u0112\u010d\3"+
		"\2\2\2\u0112\u010e\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0117\3\2\2\2\u0116\u0114\3\2"+
		"\2\2\u0117\u0118\7$\2\2\u0118\u0119\7$\2\2\u0119\u0144\7$\2\2\u011a\u011b"+
		"\7)\2\2\u011b\u011c\7)\2\2\u011c\u011d\7)\2\2\u011d\u0125\3\2\2\2\u011e"+
		"\u0124\n\r\2\2\u011f\u0120\7^\2\2\u0120\u0124\13\2\2\2\u0121\u0122\7^"+
		"\2\2\u0122\u0124\5\35\17\2\u0123\u011e\3\2\2\2\u0123\u011f\3\2\2\2\u0123"+
		"\u0121\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0126\3\2\2\2\u0125\u0123\3\2"+
		"\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7)\2\2\u0129"+
		"\u012a\7)\2\2\u012a\u0144\7)\2\2\u012b\u0133\7$\2\2\u012c\u012d\7^\2\2"+
		"\u012d\u0132\13\2\2\2\u012e\u012f\7^\2\2\u012f\u0132\5\35\17\2\u0130\u0132"+
		"\n\16\2\2\u0131\u012c\3\2\2\2\u0131\u012e\3\2\2\2\u0131\u0130\3\2\2\2"+
		"\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0136"+
		"\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u0144\7$\2\2\u0137\u013f\7)\2\2\u0138"+
		"\u0139\7^\2\2\u0139\u013e\13\2\2\2\u013a\u013b\7^\2\2\u013b\u013e\5\35"+
		"\17\2\u013c\u013e\n\17\2\2\u013d\u0138\3\2\2\2\u013d\u013a\3\2\2\2\u013d"+
		"\u013c\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0142\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0144\7)\2\2\u0143"+
		"\u0109\3\2\2\2\u0143\u011a\3\2\2\2\u0143\u012b\3\2\2\2\u0143\u0137\3\2"+
		"\2\2\u0144:\3\2\2\2\u0145\u0146\7$\2\2\u0146\u0147\7$\2\2\u0147\u0148"+
		"\7$\2\2\u0148\u014e\3\2\2\2\u0149\u014d\t\20\2\2\u014a\u014b\7^\2\2\u014b"+
		"\u014d\t\21\2\2\u014c\u0149\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u0150\3"+
		"\2\2\2\u014e\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0151\3\2\2\2\u0150"+
		"\u014e\3\2\2\2\u0151\u0152\7$\2\2\u0152\u0153\7$\2\2\u0153\u0178\7$\2"+
		"\2\u0154\u0155\7)\2\2\u0155\u0156\7)\2\2\u0156\u0157\7)\2\2\u0157\u015d"+
		"\3\2\2\2\u0158\u015c\t\20\2\2\u0159\u015a\7^\2\2\u015a\u015c\t\21\2\2"+
		"\u015b\u0158\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015f\3\2\2\2\u015d\u015e"+
		"\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u0160\3\2\2\2\u015f\u015d\3\2\2\2\u0160"+
		"\u0161\7)\2\2\u0161\u0162\7)\2\2\u0162\u0178\7)\2\2\u0163\u0169\7$\2\2"+
		"\u0164\u0168\t\22\2\2\u0165\u0166\7^\2\2\u0166\u0168\t\21\2\2\u0167\u0164"+
		"\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169"+
		"\u016a\3\2\2\2\u016a\u016c\3\2\2\2\u016b\u0169\3\2\2\2\u016c\u0178\7$"+
		"\2\2\u016d\u0173\7)\2\2\u016e\u0172\t\23\2\2\u016f\u0170\7^\2\2\u0170"+
		"\u0172\t\21\2\2\u0171\u016e\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0175\3"+
		"\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2\2\2\u0175"+
		"\u0173\3\2\2\2\u0176\u0178\7)\2\2\u0177\u0145\3\2\2\2\u0177\u0154\3\2"+
		"\2\2\u0177\u0163\3\2\2\2\u0177\u016d\3\2\2\2\u0178<\3\2\2\2\60\2y}\u0080"+
		"\u0082\u008a\u0090\u0096\u009b\u009e\u00a2\u00a8\u00ae\u00b0\u00b8\u00c0"+
		"\u00c8\u00cd\u00d3\u00d9\u00db\u00e0\u00e3\u00e7\u00ec\u00f3\u00f5\u0102"+
		"\u0112\u0114\u0123\u0125\u0131\u0133\u013d\u013f\u0143\u014c\u014e\u015b"+
		"\u015d\u0167\u0169\u0171\u0173\u0177\3\3\17\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}