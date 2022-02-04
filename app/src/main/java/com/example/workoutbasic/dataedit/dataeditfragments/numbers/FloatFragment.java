package com.example.workoutbasic.dataedit.dataeditfragments.numbers;

import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;

import androidx.annotation.NonNull;

import com.example.workoutbasic.utils.EditTextMethods;
import com.example.workoutbasic.utils.Parser;
import com.example.workoutbasic.utils.StringConverter;

public class FloatFragment extends NumberFragment {
    private double setMetric;

    @Override
    public void setEditTextParams() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        EditTextMethods.setEditTextParams(this, StringConverter.convertDouble(setMetric), editText);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        setMetric = Parser.parseDouble(editText.getText().toString()); //TODO: pass somehow
        super.onDismiss(dialog);
    }
}
