package org.angelon;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://github.com/antlr/antlr4/blob/master/doc/index.md
// https://github.com/wevre/wry/blob/master/grammars/DentLexer.g4

public class Angelon {

    public static void main(String[] args) {
        try {
            CharStream input = (CharStream) new ANTLRFileStream("test.an");
            AngelonLexer lexer = new AngelonLexer(input);
            AngelonParser parser = new AngelonParser(new CommonTokenStream(lexer));
            parser.addParseListener(new LangListener());
            parser.fileInput();
        } catch (IOException ex) {
            Logger.getLogger(Angelon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
