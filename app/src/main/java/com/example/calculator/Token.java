package com.example.calculator;

public class Token {
    public Type type;
    public Subtype subtype;
    public String string;
    public int idString;

    public enum Type{
        NUMBER,
        DOT,
        OPERATION,
        NEGOTIATION,
        FUNCTION,
        CONSTANT,
        PAR_LEFT,
        PAR_RIGHT,
        RESET,
        BACKSPACE,
        MACRO,
        EQUALS,
        NULL
    }

    public enum Subtype{
        // NUMBER
        POSITIVE,
        NEGATIVE,
        // OPERATION
        DIVISION,
        MULTIPLICATION,
        SUBTRACTION,
        ADDITION,

        // NOTHING
        NULL
    }

    public Token(){
    }

    public Token(Token token){
        this.type = token.type;
        this.subtype = token.subtype;
        this.string = token.string;
        this.idString = token.idString;
    }

    public Token(Type type, Subtype subtype, String string){
        this.type = type;
        this.string = string;
        this.subtype = subtype;
    }

    public Token(Type type, Subtype subtype){
        this.type = type;
        this.string = "";
        this.subtype = subtype;
    }

    public Token(Type type, String string){
        this.type = type;
        this.string = string;
        this.subtype = Subtype.NULL;
    }

    public Token(Type type){
        this.type = type;
        this.string = "";
        this.subtype = Subtype.NULL;
    }

    public boolean hasDot(){
        return string.contains(App.getStringFromResource(R.string.calc_dot));
    }

    public void toggleDot(){
        if (hasDot())
            string = string.replace(App.getStringFromResource(R.string.calc_dot), "");
        else
            string += App.getStringFromResource(R.string.calc_dot);
    }

    public boolean isNegative(){
        return string.contains(App.getStringFromResource(R.string.calc_sub));
    }

    public void toggleNegative() {
        if (isNegative())
            string = string.replace(App.getStringFromResource(R.string.calc_sub), "");
        else
            string = App.getStringFromResource(R.string.calc_sub) + string;
    }

    public String render(){
        switch (type){
            case FUNCTION:
                return string + App.getStringFromResource(R.string.calc_par_left);
            case NEGOTIATION:
            case RESET:
            case EQUALS:
                return "";
            default:
                return string;
        }
    }
}

