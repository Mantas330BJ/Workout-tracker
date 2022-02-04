package com.example.workoutbasic.dataedit.dataeditfragments.text;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;
import com.example.workoutbasic.dataedit.dataeditfragments.TextFragments;
import com.example.workoutbasic.utils.EditTextMethods;

import java.util.Objects;

public class TextFragment extends TextFragments { //comment, workout name
    protected EditText editText;
    private String text; //TODO: get somewhere

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.string_fragment, container, false);
    }

    @Override
    public void createView(View view) {
        editText = view.findViewById(R.id.edit_text);
        EditTextMethods.setEditTextParams(this, text, editText);
    }

    public void setNewData() {
        text = editText.getText().toString(); //TODO: set somehow
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        setNewData();
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow()
                .setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
