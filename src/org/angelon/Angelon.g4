grammar Angelon;

tokens { INDENT, DEDENT }


@lexer::header {
// Taken from: https://github.com/antlr/grammars-v4/blob/master/python/tiny-python/tiny-grammar-with-actions/Python3.g4
import org.antlr.v4.runtime.misc.Interval;
import java.util.*;
}

@lexer::members {
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
}


/*
 * parser rules
 */

type
 : Enum
 | Class
 | Tuple
 | Data
 | Int
 | Float
 | Bool
 | Set
 | List
 | Dict
 ;

/*
 * lexer rules
 */

Enum: 'enum';
Class: 'class';
Tuple: 'tuple';
Data: 'data';
Int: 'int';
Float: 'float';
Bool: 'bool';
Set: 'set';
List: 'list';
Dict: 'dict';

Newline
 : ( {self.atStartOfInput()}? Spaces | ( '\r'? '\n' | '\r' | '\f' ) Spaces?){
};

fragment Symbol: [_a-zA-Z] [_a-zA-Z0-9]*;
fragment Spaces: [ \t]+;
fragment Comment: '#' ~[\r\n\f]*;
fragment Joining: '\\' Spaces? ( '\r'? '\n' | '\r' | '\f' );

// Number constants
const : numberConst | boolConst | noneConst | strConst | bytesConst | regexConst | formatConst;
numberConst: intConst | floatConst | imaginaryConst;
intConst : DecimalInteger | OctalInteger | HexadecimalInteger | BinaryInteger;
floatConst : DecimalFloat | ExponentFloat;
imaginaryConst : ImaginaryNumber;
boolConst : Boolean;
noneConst : None;
strConst : StringArray;
bytesConst : 'b' BytesArray;
regexConst : 'r' StringArray;
formatConst : 'f' StringArray;

DecimalInteger: [1-9] [0-9]* | '0'+;
OctalInteger: '0o' [0-7]+;
HexadecimalInteger: '0x' [0-9a-fA-F]+;
BinaryInteger: '0b' [01]+;
DecimalFloat: [0-9]+? '.' [0-9]+ | '.' [0-9]+;
ExponentFloat: ([0-9]+ | DecimalFloat) [eE] [+-]? [0-9]+;
ImaginaryNumber: ( DecimalFloat | ExponentFloat | [0-9]+ ) 'j';
Boolean: 'True' | 'False';
None: 'None';
StringArray
 : '"""' ( ~'\\' | '\\' . | '\\' Newline )*? '"""'
 | '\'\'\'' ( ~'\\' | '\\' . | '\\' Newline )*? '\'\'\''
 | '"' ( '\\' . | '\\' Newline | ~[\\\r\n\f"] )* '"'
 | '\'' ( '\\' . | '\\' Newline | ~[\\\r\n\f'] )* '\''
 ;
BytesArray
 : '"""' ([\u0000-\u005B\u005D-\u007F] | '\\' [\u0000-\u007F])*? '"""'
 | '\'\'\'' ([\u0000-\u005B\u005D-\u007F] | '\\' [\u0000-\u007F])*? '\'\'\''
 | '"' ([\u0000-\u0009\u000B-\u000C\u000E-\u0021\u0023-\u005B\u005D-\u007F] | '\\' [\u0000-\u007F])* '"'
 | '\'' ([\u0000-\u0009\u000B-\u000C\u000E-\u0026\u0028-\u005B\u005D-\u007F] | '\\' [\u0000-\u007F])* '\''
 ;

