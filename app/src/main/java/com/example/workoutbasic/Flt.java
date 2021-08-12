package com.example.workoutbasic;

import android.content.Context;
import android.text.InputType;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Flt extends TextViewData {
    private float flt;

    Flt(float flt) {
        this.flt = flt;
    }

    @NonNull
    public String toString() {
        if ((int)(flt) == flt)
            return String.format(Locale.getDefault(), "%d", (int)flt);
        return String.format(Locale.getDefault(), "%.1f", flt);
    }

    //Almost total copy paste from Str
    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutEditText(fragment.getContext());
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.setText(toString());
        editView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editView.requestFocus();
        fragment.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        fragment.linearLayout.addView(editView);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        flt = Float.valueOf(((WorkoutEditText)fragment.editView).getText().toString());
        fragment.onInputListener.sendInput(this);
    }
}
