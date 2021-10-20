package com.example.calculator;

public class Key {
    public Type type;
    public String string;

    public Key() {
    }

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

    public Key(Type type, String string){
        this.type = type;
        this.string = string;
    }

    public Key(Type type){
        this.type = type;
        this.string = "";
    }
}
