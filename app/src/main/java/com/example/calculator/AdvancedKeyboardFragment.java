package com.example.calculator;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;

public class AdvancedKeyboardFragment extends KeyboardFragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advanced_keyboard_fragment, container, false);
        allButtons = ((TableLayout) view.findViewById(R.id.table)).getTouchables();
        for (View b : allButtons){
            b.setOnClickListener(this);
        }

        return view;
    }
}