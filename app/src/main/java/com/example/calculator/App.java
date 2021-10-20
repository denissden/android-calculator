package com.example.calculator;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public static String getStringFromResource(int id){
        return App.getContext().getResources().getString(id);
    }
}