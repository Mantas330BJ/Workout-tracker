package com.example.workoutbasic.dataedit.dataeditfragments.numbers;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.workoutbasic.R;
import com.example.workoutbasic.dataedit.dataeditfragments.TextFragments;

public abstract class NumberFragment extends TextFragments {
    protected EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_fragment, container, false);
    }

    @Override
    public void createView(View view) {
        editText = view.findViewById(R.id.edit_text);
        setEditTextParams();
    }

    public abstract void setEditTextParams();
}
