package com.example.workoutbasic;

import android.content.Context;
import android.text.InputType;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class Int extends TextViewData {
    private int val;

    public Int(int val) {
        this.val = val;
    }

    @NonNull
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutEditText(fragment.getContext());
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.setText(toString());
        editView.setInputType(InputType.TYPE_CLASS_NUMBER);
        editView.requestFocus();
        fragment.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(editView);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        val = Integer.valueOf(((WorkoutEditText)fragment.editView).getText().toString());
        fragment.onInputListener.sendInput(this);
    }
}
