package com.example.workoutbasic;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class Str extends TextViewData {
    private String s;

    Str(String s) {
        this.s = s;
    }

    @NonNull
    public String toString() {
        return s;
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutEditText(fragment.getContext());
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.setText(toString());
        editView.requestFocus();
        fragment.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(editView);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        s = ((WorkoutEditText)fragment.editView).getText().toString();
        fragment.onInputListener.sendInput(this);
    }
}
