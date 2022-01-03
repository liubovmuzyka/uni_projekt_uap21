
// Usercode Section ===========================================================

package de.unitrier.st.uap;

import java_cup.runtime.Symbol;

%%

// Options and Declarations Section ===========================================

/*
 * Options
 */
%class Lexer  // Name of the lexer class will be Lexer.java
%line         // Enables line count, accessed by yyline
%column       // Enables column count, accessed by yycolumn
%cup          // CUP compatibility mode

/*
 * Declarations
 */
%{
    /*
     * Creates a new java_cup.runtime.Symbol with given type
     * and no value, saves current line and column count
     * @param type - the type of the new symbol
     */
    private Symbol createSymbol(int type)
    {
        return new Symbol(type, yyline, yycolumn);
    }


    /*
     * Creates a new java_cup.runtime.Symbol with given type
     * and value, saves current line and column count
     * @param type - the type of the new symbol
     * @param attribute - the attribute of the new symbol
     */
    private Symbol createSymbol(int type, Object attribute)
    {
        return new Symbol(type, yyline, yycolumn, attribute);
    }
%}


/*
 * Macro Declarations
 */

LineTerminator  = \r | \n | \r\n
WhiteSpace      = {LineTerminator} | [ \t\f]
PositiveInteger = 0 | [1-9][0-9]*
Identifier = [A-Za-z_][A-Za-z0-9_]*

/* Comments */
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
InputCharacter = [^\r\n]
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"
Comment = {TraditionalComment} | {EndOfLineComment} |
          {DocumentationComment}

%%

// Lexical Rules Section ======================================================

/* Lexical Rules */
<YYINITIAL> {

    // Keyword tokens
    "let"           { return createSymbol(sym.LET); }
    "in"			{ return createSymbol(sym.IN); }
    "if"			{ return createSymbol(sym.IF); }
    "then"		    { return createSymbol(sym.THEN); }
    "else"		    { return createSymbol(sym.ELSE); }
    "while"		    { return createSymbol(sym.WHILE); }
    "do"			{ return createSymbol(sym.DO); }

    "=="			{ return createSymbol(sym.EQEQ); }
    "!="			{ return createSymbol(sym.NEQ); }
    "<"			    { return createSymbol(sym.LT); }
    ">"			    { return createSymbol(sym.GT); }
    ">="			{ return createSymbol(sym.GOE); }
    "<="			{ return createSymbol(sym.LOE); }
    "&&"			{ return createSymbol(sym.AND); }
    "||"			{ return createSymbol(sym.OR); }

    "+"		        { return createSymbol(sym.PLUS); }
    "-"		        { return createSymbol(sym.MIN); }
    "/"			    { return createSymbol(sym.DIV); }
    "*"			    { return createSymbol(sym.MULT); }

    "("			    { return createSymbol(sym.STARTBR); }
    ")"			    { return createSymbol(sym.ENDBR); }
    "{"			    { return createSymbol(sym.STARTCB); }
    "}"			    { return createSymbol(sym.ENDCB); }
    ";"			    { return createSymbol(sym.SEMI); }
    ","			    { return createSymbol(sym.COMMA); }
    "="			    { return createSymbol(sym.ASSIGN); }

    "true"			{ return createSymbol(sym.TRUE); }

    "false"			{ return createSymbol(sym.FALSE); }

    // Positive integers
    {PositiveInteger} { return createSymbol( sym.CONST, Integer.parseInt(yytext()) ); }

    // Identifiers
    {Identifier} { return createSymbol( sym.ID, yytext() ); }

    // White space
    {WhiteSpace} { /* do nothing */ }
	
	// Comments
	{Comment}     { /* do nothing */ }

}

// If the input did not match one of the rules above, throw an illegal character IOException
[^] { throw new java.io.IOException("Illegal character <" + yytext() + "> at line " + yyline + ", column " + yycolumn); }

