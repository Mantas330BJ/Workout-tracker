package com.example.workoutbasic;

import android.text.InputFilter;
import android.text.InputType;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.Locale;
import java.util.Objects;

public class Flt extends TextViewData {
    private float flt;

    Flt(float flt) {
        this.flt = flt;
    }

    public float getFlt() {
        return flt;
    }

    @NonNull
    public String toString() {
        int precision = 0;
        float tflt = flt, epsilon = (float) 1e-3;
        while (Math.abs((int)tflt - tflt) > epsilon) {
            tflt *= 10;
            ++precision;
        }

        String format = "%." + precision + "f";
        return String.format(Locale.getDefault(), format, flt);
    }

    //Almost total copy paste from Str
    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutEditText(fragment.requireContext());
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;

        int maxLength = 8;
        editView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

        editView.setText(toString());
        editView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editView.requestFocus();
        Objects.requireNonNull(fragment.getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(editView);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        flt = Float.parseFloat(Objects.requireNonNull(((WorkoutEditText) fragment.editView).getText()).toString());
        callOnChange();
        fragment.onInputListener.sendInput(this);
    }
}
