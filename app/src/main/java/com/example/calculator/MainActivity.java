package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kotlin.Triple;

public class MainActivity extends AppCompatActivity implements KeyboardInterface{

    private Expression expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = new Expression();
    }

    @Override
    public void onButtonClick(String msg){
        Key key = KeyboardTable.ConvertKey(msg);
        expression.append(new Token(key));

        Log.d("clicked", key.string + " " + key.type.toString());

        renderText();
    }

    public void onBackspaceClick(View v){
        Log.d("clicked", "BACKSPACE");
        expression.append(new Token(Key.Type.BACKSPACE));

        renderText();
    }

    public void renderText(){
        TextView main_text = findViewById(R.id.textView_main);
        main_text.setText(expression.render());
    }

    // save state on rotation
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("main_text",     ((TextView)findViewById(R.id.textView_main))    .getText().toString());
        outState.putString("result_text",   ((TextView)findViewById(R.id.textView_result))  .getText().toString());
        outState.putInt("expression",       ObjectBundle.put(expression));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ((TextView)findViewById(R.id.textView_main))    .setText(savedInstanceState.getString("main_text"));
        ((TextView)findViewById(R.id.textView_result))  .setText(savedInstanceState.getString("result_text"));
        expression = (Expression) ObjectBundle.get(savedInstanceState.getInt("expression"));
    }
    // END save state on rotation
}