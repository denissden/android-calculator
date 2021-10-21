package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity implements KeyboardInterface{

    private Expression expression;
    JsEvaluator jsEvaluator = new JsEvaluator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = new Expression();
    }

    @Override
    public void onButtonClick(String msg){
        Token key = TokenTable.stringToToken(msg);
        expression.append(key);

        if (key.type == Token.Type.EQUALS){
            solveExpressionIntoTextView(findViewById(R.id.textView_result));
        }

        Log.d("clicked", key.string + " " + key.type.toString() + " " + key.subtype.toString());

        renderText();
    }

    public void solveExpressionIntoTextView(TextView textView){
        textView.setText("");
        String js = ExpressionCompiler.compile(expression);
        jsEvaluator.evaluate(js, new JsCallback() {
            @Override
            public void onResult(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(String s) {
                textView.setText(App.getStringFromResource(R.string.calc_error));
            }
        });
    }

    public void onBackspaceClick(View v){
        Log.d("clicked", "BACKSPACE");
        expression.append(new Token(Token.Type.BACKSPACE));

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