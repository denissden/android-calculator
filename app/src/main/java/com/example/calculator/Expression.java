package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    List<Token> tokenList;

    public Expression(){
        tokenList = new ArrayList<Token>();
    }

    public void append(Token token){
        Token last = lastToken();
        switch (token.type){
            case NUMBER:
                if (last.type == Token.Type.NUMBER)
                    last.string += token.string;
                else
                    tokenList.add(token);
                if (last.type == Token.Type.NEGOTIATION)
                    token.toggleNegative();
                break;
            case DOT:
                if (last.type == Token.Type.NUMBER)
                    last.toggleDot();
                else {
                    token.type = Token.Type.NUMBER;
                    tokenList.add(token);
                }
                break;
            case NEGOTIATION:
                if (last.type == Token.Type.NUMBER)
                    last.toggleNegative();
                else if (last.type == Token.Type.NEGOTIATION)
                    removeLast();
                else
                    tokenList.add(token);
                break;

            case FUNCTION:
            case MACRO:
            case CONSTANT:
                if (    last.type != Token.Type.NULL &&
                        last.type != Token.Type.PAR_LEFT &&
                        last.type != Token.Type.OPERATION &&
                        last.type != Token.Type.MACRO &&
                        last.type != Token.Type.FUNCTION)
                    tokenList.add(new Token(Token.Type.OPERATION,
                            App.getContext().getResources().getString(R.string.calc_mul)));
                tokenList.add(token);
                break;

            case BACKSPACE:
                Log.d("applied", "BACKSPACE");
                if (last.type == Token.Type.NUMBER && last.string.length() > 1)
                    last.string = last.string.substring(0, last.string.length() - 1);
                else
                    removeLast();
                break;

            default:
                tokenList.add(token);
        }
    }

    public String render(){
        StringBuilder rendered_string = new StringBuilder();
        for (Token token : tokenList){
            rendered_string.append(token.render());
        }
        return rendered_string.toString();
    }

    public Token lastToken(){
        return tokenList.size() > 0 ? tokenList.get(tokenList.size() - 1) : new Token(Token.Type.NULL);
    }

    public void removeLast(){
        if (tokenList.size() > 0) tokenList.remove(tokenList.size() - 1);
    }
}
