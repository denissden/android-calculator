package com.example.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardTable {
    private static String _get(int id){
        return App.getStringFromResource(id);
    }

    private static boolean _eq(String str, int id){
        return str.equals(_get(id));
    }

    public static List<String > operations = new ArrayList<String>()
    {{
            add(_get(R.string.calc_div  ));
            add(_get(R.string.calc_mul  ));
            add(_get(R.string.calc_sub  ));
            add(_get(R.string.calc_add  ));
    }};

    public static List<String > constants = new ArrayList<String>()
    {{
        add(_get(R.string.calc_pi   ));
        add(_get(R.string.calc_e    ));
    }};

    public static Map<String, String> functions = new HashMap<String, String>(){{
        put(_get(R.string.calc_sqrt),   "pow2");
        put(_get(R.string.calc_pow2),   "pow2");
        put(_get(R.string.calc_pow3),   "pow3");
        put(_get(R.string.calc_2pow),   "2pow");
        put(_get(R.string.calc_sin),    "sin");
        put(_get(R.string.calc_cos),    "cos");
        put(_get(R.string.calc_tan),    "tan");
        put(_get(R.string.calc_log),    "log");
        put(_get(R.string.calc_ln),     "ln");
        put(_get(R.string.calc_abs),    "abs");
    }};

    public static Key ConvertKey(String key_string){
        // number keys
        if      ("1234567890".contains(key_string))     return new Key(Key.Type.NUMBER, key_string);
        else if (_eq(key_string, R.string.calc_dot))    return new Key(Key.Type.DOT, key_string);
        else if (_eq(key_string, R.string.calc_neg))    return new Key(Key.Type.NEGOTIATION);
        else if (_eq(key_string, R.string.calc_reset))  return new Key(Key.Type.RESET);
        else if (_eq(key_string, R.string.calc_eq))     return new Key(Key.Type.EQUALS);
        // parentheses
        else if (_eq(key_string, R.string.calc_par_left))    return new Key(Key.Type.PAR_LEFT, key_string);
        else if (_eq(key_string, R.string.calc_par_right))    return new Key(Key.Type.PAR_RIGHT, key_string);
        // operation keys like +-*/
        else if (operations.contains(key_string))       return new Key(Key.Type.OPERATION, key_string);
        else if
        (_eq(key_string, R.string.calc_one_over))       return new Key(Key.Type.MACRO,"1" + _get(R.string.calc_div));
        else if (constants.contains(key_string))        return new Key(Key.Type.CONSTANT, key_string);
        // other keys default to functions
        else
            return new Key(Key.Type.FUNCTION, functions.getOrDefault(key_string, key_string));
    }
}

