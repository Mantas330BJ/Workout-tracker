package com.example.workoutbasic;

import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.Objects;

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
        fragment.editView = new WorkoutEditText(fragment.requireContext());
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.setText(toString());
        editView.requestFocus();
        Objects.requireNonNull(fragment.getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(editView);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        s = Objects.requireNonNull(((WorkoutEditText) fragment.editView).getText()).toString();
        fragment.onInputListener.sendInput(this);
    }
}
