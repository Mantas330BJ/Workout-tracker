package com.example.workoutbasic;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Str extends TextViewData {
    private String s;

    Str(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editText = new EditText(fragment.getContext());
        fragment.editText.setText(toString());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10 ,10 ,10);
        fragment.editText.setLayoutParams(params);
        fragment.editText.setEms(20);
        fragment.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        fragment.editText.requestFocus();
        fragment.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(fragment.editText);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        String input = fragment.editText.getText().toString();
        fragment.onInputListener.sendInput(input);
    }
}
