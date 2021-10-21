package com.example.calculator;

import java.util.HashMap;
import java.util.Map;

public class ExpressionCompiler {
    private static String _get(int id){
        return App.getStringFromResource(id);
    }

    private static boolean _eq(String str, int id){
        return str.equals(_get(id));
    }

    public static Map<Integer, String> stringToJS = new HashMap<Integer, String>(){
        {
            put(R.string.calc_par_left, "(");
            put(R.string.calc_par_right, ")");
            put(R.string.calc_div, "/");
            put(R.string.calc_mul, "*");
            put(R.string.calc_sub, "-");
            put(R.string.calc_add, "+");
            put(R.string.calc_neg, "");
            put(R.string.calc_dot, "");
            put(R.string.calc_sqrt, "Math.sqrt(");
            put(R.string.calc_pow2, "_pow2(");
            put(R.string.calc_pow3, "_pow3(");
            put(R.string.calc_2pow, "_2pow(");
            put(R.string.calc_sin, "Math.sin(");
            put(R.string.calc_cos, "Math.cos(");
            put(R.string.calc_tan, "Math.tan(");
            put(R.string.calc_log, "Math.log10(");
            put(R.string.calc_ln, "Math.log(");
            put(R.string.calc_abs, "Math.abs(");
            put(R.string.calc_pi, "Math.PI");
            put(R.string.calc_e, "Math.E");
            put(R.string.calc_one_over, "1/");
        }};

    private static final String jsFunctions =
            "var _2pow = x => Math.pow(2, x);" +
            "var _pow2 = x => Math.pow(x, 2);" +
            "var _pow3 = x => Math.pow(x, 3);";

    public static String compile(Expression expression){
        StringBuilder stringBuilder = new StringBuilder();
        for (Token token : expression.tokenList){
            stringBuilder.append(stringToJS.getOrDefault(token.idString, token.string));
        }
        stringBuilder.insert(0, jsFunctions);

        return stringBuilder.toString();
    }
}
