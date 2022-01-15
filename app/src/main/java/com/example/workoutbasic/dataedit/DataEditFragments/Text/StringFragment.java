package com.example.workoutbasic.dataedit.DataEditFragments.Text;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import java.util.Objects;

import com.example.workoutbasic.dataedit.DataEditFragments.TextFragments;
import com.example.workoutbasic.utils.EditTextMethods;
import com.example.workoutbasic.variables.StringPasser;

public class StringFragment extends TextFragments {
    protected EditText editText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.string_fragment, container, false);
    }

    @Override
    public void createView(View view) {
        editText = view.findViewById(R.id.edit_text);
        EditTextMethods.setEditTextParams(this, parentData.toString(), editText);
    }

    public void setNewData() {
        ((StringPasser)parentData).setStr(editText.getText().toString());
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        setNewData();
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
