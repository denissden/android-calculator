package com.example.calculator;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;

public class KeyboardFragment extends Fragment implements View.OnClickListener{

    private KeyboardViewModel mViewModel;
    KeyboardInterface keyboardInterface;
    ArrayList<View> allButtons;

    public static KeyboardFragment newInstance() {
        return new KeyboardFragment();
    }


//    public interface KeyboardInterface {
//        void onButtonClick(String msg);
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.keyboard_fragment, container, false);
        allButtons = ((TableLayout) view.findViewById(R.id.table)).getTouchables();
        for (View b : allButtons){
            b.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KeyboardViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onAttach(@NonNull Context context) {
        keyboardInterface = (KeyboardInterface)context;
        super.onAttach(context);
    }

    @Override
    public void onDetach(){
        super.onDetach();
        keyboardInterface = null;
    }

    public void onClick(View v){
        if (keyboardInterface != null){
            keyboardInterface.onButtonClick(((Button)v).getText().toString());
        }
    }
}