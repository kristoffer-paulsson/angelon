grammar Angelon;

import Dent;

tokens { Indent, Dedent }

@lexer::members {
	private int INDENT_TOKEN = AngelonParser.Indent;
	private int DEDENT_TOKEN = AngelonParser.Dedent;
}

singleInput
 : NewLine
 | simpleStmt
 | compoundStmt NewLine
 ;

fileInput
 : ( NewLine | stmt )* EOF
 ;

evalInput
 : testList NewLine* EOF
 ;

decorator
 : '@' dottedName ( '(' argList? ')' )? NewLine
 ;

decorators
 : decorator+
 ;

decorated
 : decorators ( classDef | funcDef )
 ;

funcDef
 : Def Symbol parameters ( '->' test )? ':' suite
 ;

parameters
 : '(' typedArgsList? ')'
 ;

typedArgsList
 : varDef ( '=' test )? ( ',' varDef ( '=' test )? )* ( ',' ( '*' varDef? ( ',' varDef ( '=' test )? )* ( ',' '**' varDef )?
                                                            | '**' varDef
                                                            )? 
                                                      )?
 | '*' varDef? ( ',' varDef ( '=' test )? )* ( ',' '**' varDef )?
 | '**' varDef
 ;

varDef
 : Symbol ( ':' test )?
 ;

// REMOVE OR CHANGE
varargslist
 : vfpdef ( '=' test )? ( ',' vfpdef ( '=' test )? )* ( ',' ( '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )? 
                                                            | '**' vfpdef 
                                                            )? 
                                                      )?
 | '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
 | '**' vfpdef
 ;

// REMOVE
vfpdef
 : Symbol
 ;

stmt
 : simpleStmt
 | compoundStmt
 ;

simpleStmt
 : smallStmt ( ';' smallStmt )* ';'? NewLine
 ;

smallStmt
 : exprStmt
 | delStmt
 | passStmt
 | flowStmt
 | importStmt
 | globalStmt
 | nonLocalStmt
 | assertStmt
 ;

exprStmt
 : testlistStarExpr ( augAssign ( yieldExpr | testList)
                      | ( '=' ( yieldExpr| testlistStarExpr ) )*
                      )
 ;           

testlistStarExpr
 : ( test | starExpr ) ( ',' ( test |  starExpr ) )* ','?
 ;

augAssign
 : '+=' 
 | '-=' 
 | '*=' 
 | '@='
 | '/=' 
 | '%=' 
 | '&=' 
 | '|=' 
 | '^=' 
 | '<<=' 
 | '>>=' 
 | '**=' 
 | '//='
 ;

delStmt
 : Del exprlist
 ;

passStmt
 : Pass
 ;

flowStmt
 : breakStmt
 | continueStmt
 | returnStmt
 | raiseStmt
 | yieldStmt
 ;

breakStmt
 : Break
 ;

continueStmt
 : Continue
 ;

returnStmt
 : Return testList?
 ;

yieldStmt
 : yieldExpr
 ;

raiseStmt
 : Raise ( test ( From test )? )?
 ;

importStmt
 : importName
 | importFrom
 ;

importName
 : Import dottedAsNames
 ;

importFrom
 : From ( ( '.' | '...' )* dottedName
        | ('.' | '...')+ 
        )
   Import ( '*'
          | '(' importAsNames ')'
          | importAsNames
          )         
 ;

importAsName
 : Symbol ( As Symbol )?
 ;

dottedAsName
 : dottedName ( As Symbol )?
 ;

importAsNames
 : importAsName ( ',' importAsName )* ','?
 ;

dottedAsNames
 : dottedAsName ( ',' dottedAsName )*
 ;

dottedName
 : Symbol ( '.' Symbol )*
 ;

globalStmt
 : Global Symbol ( ',' Symbol )*
 ;

nonLocalStmt
 : NonLocal Symbol ( ',' Symbol )*
 ;

assertStmt
 : Assert test ( ',' test )?
 ;

compoundStmt
 : ifStmt
 | whileStmt
 | forStmt
 | tryStmt
 | withStmt
 | funcDef
 | classDef
 | decorated
 ;

ifStmt
 : If test ':' suite ( Elif test ':' suite )* ( Else ':' suite )?
 ;

whileStmt
 : While test ':' suite ( Else ':' suite )?
 ;

forStmt
 : For exprlist In testList ':' suite ( Else ':' suite )?
 ;

tryStmt
 : Try ':' suite ( ( exceptClause ':' suite )+
                   ( Else ':' suite )?
                   ( Finally ':' suite )?
                 | Finally ':' suite
                 )
 ;

withStmt
 : With withItem ( ',' withItem )* ':' suite
 ;

withItem
 : test ( As expr )?
 ;

exceptClause
 : Except ( test ( As Symbol )? )?
 ;

suite
 : simpleStmt
 | NewLine Indent stmt+ Dedent
 ;

test
 : orTest ( If orTest Else test )?
 | lambdaDef
 ;

testNoCond
 : orTest
 | lambdaDefNoCond
 ;

lambdaDef
 : Lambda varargslist? ':' test
 ;

lambdaDefNoCond
 : Lambda varargslist? ':' testNoCond
 ;

orTest
 : andTest ( Or andTest )*
 ;

andTest
 : notTest ( And notTest )*
 ;

notTest
 : Not notTest
 | comparison
 ;

comparison
 : starExpr ( compOp starExpr )*
 ;

compOp
 : '<'
 | '>'
 | '=='
 | '>='
 | '<='
 | '<>'
 | '!='
 | In
 | Not In
 | Is
 | Is Not
 ;

starExpr
 : '*'? expr
 ;

expr
 : xorExpr ( '|' xorExpr )*
 ;

xorExpr
 : andExpr ( '^' andExpr )*
 ;

andExpr
 : shiftExpr ( '&' shiftExpr )*
 ;

shiftExpr
 : arithExpr ( '<<' arithExpr
              | '>>' arithExpr
              )*
 ;

arithExpr
 : term ( '+' term
        | '-' term 
        )*
 ;

term
 : factor ( '*' factor
          | '/' factor
          | '%' factor 
          | '//' factor 
          | '@' factor
          )*
 ;

factor
 : '+' factor 
 | '-' factor 
 | '~' factor 
 | power
 ;

power
 : atom trailer* ( '**' factor )?
 ;

atom
 : '(' ( yieldExpr | testlistComp )? ')'
 | '[' testlistComp? ']'
 | '{' dictOrSetMaker? '}'
 | Symbol
 | number 
 | str+ 
 | '...' 
 | None
 | True
 | False
 ;

testlistComp
 : test ( compFor
        | ( ',' test )* ','? 
        )
 ;

trailer
 : '(' argList? ')'
 | '[' subscriptList ']'
 | '.' Symbol
 ;

subscriptList
 : subscript ( ',' subscript )* ','?
 ;

subscript
 : test 
 | test? ':' test? sliceOp?
 ;

sliceOp
 : ':' test?
 ;

exprlist
 : starExpr ( ',' starExpr )* ','?
 ;

testList
 : test ( ',' test )* ','?
 ;

// REMOVE
dictOrSetMaker
 : test ':' test ( compFor
                 | ( ',' test ':' test )* ','? 
                 ) 
 | test ( compFor
        | ( ',' test )* ','? 
        )
 ;

classDef
 : Class Symbol ( '(' argList? ')' )? ':' suite
 ;

argList
 : ( argument ',' )* ( argument ','?
                     | '*' test ( ',' argument )* ( ',' '**' test )?
                     | '**' test
                     )
 ;

argument
 : test compFor?
 | test '=' test
 ;

compIter
 : compFor
 | compIf
 ;

compFor
 : For exprlist In orTest compIter?
 ;

compIf
 : If testNoCond compIter?
 ;

// REMOVE
yieldExpr
 : Yield yieldArg?
 ;

yieldArg
 : From test
 | testList
 ;

str
 : StringLiteral
 | BytesLiteral
 ;

number
 : integer
 | FloatNumber
 | ImaginaryNumber
 ;

integer
 : DecimalInteger
 | OctalIinteger
 | HexadecimalInteger
 | BinaryInteger
 ;

Def : 'def';
Return : 'return';
Raise : 'raise';
From : 'from';
Import : 'import';
Include: 'include';
As : 'as';
Global : 'global'; // REMOVE
NonLocal : 'nonlocal';
Assert : 'assert'; // REMOVE
If : 'if';
Elif : 'elif';
Else : 'else';
While : 'while';
For : 'for';
In : 'in';
Try : 'try';
Finally : 'finally';
With : 'with';
Except : 'except';
Lambda : 'lambda';
Or : 'or';
And : 'and';
Not : 'not';
Is : 'is';
None : 'None';
True : 'True';
False : 'False';
Class : 'class';
Yield : 'yield';
Del : 'del';
Pass : 'pass';
Continue : 'continue';
Break : 'break';

// NewLine : ( '\r'? '\n' | '\r' | '\f' ) Spaces?;

Symbol
 : IdStart IdContinue*
 ;

StringLiteral
 : [uU]? [rR]? ( ShortString | LongString )
 ;

BytesLiteral
 : [bB] [rR]? ( ShortBytes | LongBytes )
 ;

DecimalInteger
 : NonZeroDigit Digit*
 | '0'+
 ;

OctalIinteger
 : '0' [oO] OctDigit+
 ;

HexadecimalInteger
 : '0' [xX] HexDigit+
 ;

BinaryInteger
 : '0' [bB] BinDigit+
 ;

FloatNumber
 : PointFloat
 | ExponentFloat
 ;

ImaginaryNumber
 : ( FloatNumber | IntPart ) 'j'
 ;

DOT : '.';
ELLIPSIS : '...';
STAR : '*';
OPEN_PAREN : '(';
CLOSE_PAREN : ')';
COMMA : ',';
COLON : ':';
SEMI_COLON : ';';
POWER : '**';
ASSIGN : '=';
OPEN_BRACK : '[';
CLOSE_BRACK : ']';
OR_OP : '|';
XOR : '^';
AND_OP : '&';
LEFT_SHIFT : '<<';
RIGHT_SHIFT : '>>';
ADD : '+';
MINUS : '-';
DIV : '/';
MOD : '%';
IDIV : '//';
NOT_OP : '~';
OPEN_BRACE : '{';
CLOSE_BRACE : '}';
LESS_THAN : '<';
GREATER_THAN : '>';
EQUALS : '==';
GT_EQ : '>=';
LT_EQ : '<=';
NOT_EQ_1 : '<>';
NOT_EQ_2 : '!=';
AT : '@';
ARROW : '->';
ADD_ASSIGN : '+=';
SUB_ASSIGN : '-=';
MULT_ASSIGN : '*=';
AT_ASSIGN : '@=';
DIV_ASSIGN : '/=';
MOD_ASSIGN : '%=';
AND_ASSIGN : '&=';
OR_ASSIGN : '|=';
XOR_ASSIGN : '^=';
LEFT_SHIFT_ASSIGN : '<<=';
RIGHT_SHIFT_ASSIGN : '>>=';
POWER_ASSIGN : '**=';
IDIV_ASSIGN : '//=';

SKIP_
 : ( Spaces | Comment | LineJoining ) -> skip
 ;

UNKNOWN_CHAR
 : .
 ;

fragment ShortString
 : '\'' ( StringEscapeSeq | ~[\\\r\n\f'] )* '\''
 | '"' ( StringEscapeSeq | ~[\\\r\n\f"] )* '"'
 ;

fragment LongString
 : '\'\'\'' LongStringItem*? '\'\'\''
 | '"""' LongStringItem*? '"""'
 ;

fragment LongStringItem
 : LongStringChar
 | StringEscapeSeq
 ;

fragment LongStringChar
 : ~'\\'
 ;

fragment StringEscapeSeq
 : '\\' .
 ;

fragment NonZeroDigit
 : [1-9]
 ;

fragment Digit
 : [0-9]
 ;

fragment OctDigit
 : [0-7]
 ;

fragment HexDigit
 : [0-9a-fA-F]
 ;

fragment BinDigit
 : [01]
 ;

fragment PointFloat
 : IntPart? Fraction
 | IntPart '.'
 ;

fragment ExponentFloat
 : ( IntPart | PointFloat ) Exponent
 ;

fragment IntPart
 : Digit+
 ;

fragment Fraction
 : '.' Digit+
 ;

fragment Exponent
 : [eE] [+-]? Digit+
 ;

fragment ShortBytes
 : '\'' ( ShortBytesCharNoSingleQoute | BytesEscapeSeq )* '\''
 | '"' ( ShortBytesCharNoDoubleQuote | BytesEscapeSeq )* '"'
 ;

fragment LongBytes
 : '\'\'\'' LongBytesItem*? '\'\'\''
 | '"""' LongBytesItem*? '"""'
 ;

fragment LongBytesItem
 : LongBytesChar
 | BytesEscapeSeq
 ;

fragment ShortBytesCharNoSingleQoute
 : [\u0000-\u0009]
 | [\u000B-\u000C]
 | [\u000E-\u0026]
 | [\u0028-\u005B]
 | [\u005D-\u007F]
 ; 

fragment ShortBytesCharNoDoubleQuote
 : [\u0000-\u0009]
 | [\u000B-\u000C]
 | [\u000E-\u0021]
 | [\u0023-\u005B]
 | [\u005D-\u007F]
 ; 

fragment LongBytesChar
 : [\u0000-\u005B]
 | [\u005D-\u007F]
 ;

fragment BytesEscapeSeq
 : '\\' [\u0000-\u007F]
 ;

fragment Comment
 : '#' ~[\r\n\f]*
 ;

fragment LineJoining
 : '\\' Spaces? ( '\r'? '\n' | '\r' | '\f' )
 ;

fragment OtherIdStart
 : [\u2118\u212E\u309B\u309C]
 ;

fragment IdStart
 : '_'
 | [\p{Letter}\p{Letter_Number}]
 | OtherIdStart
 ;

fragment IdContinue
 : IdStart
 | [\p{Nonspacing_Mark}\p{Spacing_Mark}\p{Decimal_Number}\p{Connector_Punctuation}\p{Format}]
 ;