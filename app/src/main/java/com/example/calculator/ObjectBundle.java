package com.example.calculator;

import java.util.HashMap;
import java.util.Map;

public class ObjectBundle {
    public static Map<Integer, Object> objects = new HashMap<Integer, Object>();

    public static int put(Object object){
        int key = object.hashCode();
        objects.put(key, object);
        return key;
    }

    public static <T> Object get(int key){
        T object = (T) objects.get(key);
        objects.remove(key);
        return object;
    }
}
