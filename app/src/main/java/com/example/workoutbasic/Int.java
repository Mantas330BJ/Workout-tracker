package com.example.workoutbasic;

import android.text.InputFilter;
import android.text.InputType;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Int extends TextViewData {
    private int val;

    public Int(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @NonNull
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutEditText(fragment.requireContext());
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;

        int maxLength = 8;
        editView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

        editView.setText(toString());
        editView.setInputType(InputType.TYPE_CLASS_NUMBER);
        editView.requestFocus();
        Objects.requireNonNull(fragment.getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(editView);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        val = Integer.parseInt(Objects.requireNonNull(((WorkoutEditText) fragment.editView).getText()).toString());
        callOnChange();
        fragment.onInputListener.sendInput(this);
    }
}
