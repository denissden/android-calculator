package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

public class Expression {
    List<Token> tokenList;
    public static Token mulToken = new Token(Token.Type.OPERATION,
            App.getContext().getResources().getString(R.string.calc_mul)){{ idString = R.string.calc_mul;}};

    public Expression(){
        tokenList = new ArrayList<Token>();
    }

    public void append(Token token){
        Token last = lastToken();
        switch (token.type){
            case NUMBER:
                if (last.type == Token.Type.NUMBER)
                    last.string += token.string;
                else {
                    if (last.type == Token.Type.PAR_RIGHT)
                        tokenList.add(new Token(mulToken));
                    tokenList.add(token);
                }
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
                switch (last.type){
                    case NUMBER:
                        last.toggleNegative();
                        break;
                    case NEGOTIATION:
                        removeLast();
                        break;
                    case OPERATION:
                        if (last.subtype == Token.Subtype.ADDITION) {
                            last.subtype = Token.Subtype.SUBTRACTION;
                            last.string = App.getStringFromResource(R.string.calc_sub);
                        }
                        else if (last.subtype == Token.Subtype.SUBTRACTION) {
                            last.subtype = Token.Subtype.ADDITION;
                            last.string = App.getStringFromResource(R.string.calc_add);
                        }
                        break;
                    default:
                        tokenList.add(token);
                }
                break;

            case PAR_LEFT:
            case FUNCTION:
            case MACRO:
            case CONSTANT:
                if (    last.type != Token.Type.NULL &&
                        last.type != Token.Type.PAR_LEFT &&
                        last.type != Token.Type.OPERATION &&
                        last.type != Token.Type.MACRO &&
                        last.type != Token.Type.FUNCTION)
                    tokenList.add(new Token(mulToken));
                tokenList.add(token);
                break;

            case OPERATION:
                if (last.type == Token.Type.OPERATION)
                    removeLast();
                else if (last.type != Token.Type.NULL &&
                        last.type != Token.Type.PAR_LEFT &&
                        last.type != Token.Type.MACRO &&
                        last.type != Token.Type.FUNCTION)
                    tokenList.add(token);
                break;

            case BACKSPACE:
                Log.d("applied", "BACKSPACE");
                if (last.type == Token.Type.NUMBER && last.string.length() > 1)
                    last.string = last.string.substring(0, last.string.length() - 1);
                else
                    removeLast();
                break;

            case PAR_RIGHT:
                if (last.type == Token.Type.PAR_LEFT)
                    removeLast();
                else if (last.type != Token.Type.FUNCTION &&
                        last.type != Token.Type.OPERATION)
                    tokenList.add(token);
                break;

            case RESET:
                tokenList.clear();
                break;

            default:
                Log.d("DEFAULT (not appended)", token.type.toString());
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
