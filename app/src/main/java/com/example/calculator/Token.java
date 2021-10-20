package com.example.calculator;

public class Token extends Key{
    public Token(Key key){
        this.type = key.type;
        this.string = key.string;
    }

    public Token(Type type, String string){
        this.type = type;
        this.string = string;
    }

    public Token(Type type){
        this.type = type;
    }

    public boolean hasDot(){
        return string.contains(App.getStringFromResource(R.string.calc_dot));
    }

    public void toggleDot(){
        if (hasDot())
            string = string.replace(App.getStringFromResource(R.string.calc_dot), "");
        else
            string += string;
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

