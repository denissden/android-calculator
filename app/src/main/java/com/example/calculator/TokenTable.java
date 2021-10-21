package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class TokenTable {
    private static String _get(int id){
        return App.getStringFromResource(id);
    }

    private static boolean _eq(String str, int id){
        return str.equals(_get(id));
    }

    public static Map<String, Token> stringToToken = new HashMap<String, Token>(){
        private void _put(int id, Token t) {
            t.idString = id;
            if (t.string == null || t.string.equals(""))
                t.string = App.getStringFromResource(id);
            put(_get(id), t);
        }
        {

            _put(R.string.calc_reset, new Token(Token.Type.RESET));
            _put(R.string.calc_eq, new Token(Token.Type.EQUALS));

            _put(R.string.calc_par_left, new Token(Token.Type.PAR_LEFT));
            _put(R.string.calc_par_right, new Token(Token.Type.PAR_RIGHT));

            _put(R.string.calc_div, new Token(Token.Type.OPERATION, Token.Subtype.DIVISION));
            _put(R.string.calc_mul, new Token(Token.Type.OPERATION, Token.Subtype.MULTIPLICATION));
            _put(R.string.calc_sub, new Token(Token.Type.OPERATION, Token.Subtype.SUBTRACTION));
            _put(R.string.calc_add, new Token(Token.Type.OPERATION, Token.Subtype.ADDITION));

            _put(R.string.calc_neg, new Token(Token.Type.NEGOTIATION));
            _put(R.string.calc_dot, new Token(Token.Type.DOT));
            // NUMBERS are processed separately
            _put(R.string.calc_sqrt, new Token(Token.Type.FUNCTION, "sqrt"));
            _put(R.string.calc_pow2, new Token(Token.Type.FUNCTION, "pow2"));
            _put(R.string.calc_pow3, new Token(Token.Type.FUNCTION, "pow3"));
            _put(R.string.calc_2pow, new Token(Token.Type.FUNCTION, "2pow"));
            _put(R.string.calc_sin, new Token(Token.Type.FUNCTION));
            _put(R.string.calc_cos, new Token(Token.Type.FUNCTION));
            _put(R.string.calc_tan, new Token(Token.Type.FUNCTION));
            _put(R.string.calc_log, new Token(Token.Type.FUNCTION));
            _put(R.string.calc_ln, new Token(Token.Type.FUNCTION));
            _put(R.string.calc_abs, new Token(Token.Type.FUNCTION, "abs"));

            _put(R.string.calc_e, new Token(Token.Type.CONSTANT));
            _put(R.string.calc_pi, new Token(Token.Type.CONSTANT));

            _put(R.string.calc_one_over, new Token(Token.Type.MACRO, "1" + _get(R.string.calc_div)));
            _put(R.string.calc_backspace, new Token(Token.Type.BACKSPACE));
        }};

    public static Token stringToToken(String key_string){
        // number keys
        if      ("1234567890".contains(key_string))
            return new Token(Token.Type.NUMBER, key_string);
        else if (stringToToken.containsKey(key_string))
            return new Token(stringToToken.get(key_string));
        else
            return new Token(Token.Type.NULL);
    }
}

