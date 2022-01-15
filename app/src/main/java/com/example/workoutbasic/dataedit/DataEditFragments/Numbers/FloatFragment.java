package com.example.workoutbasic.dataedit.DataEditFragments.Numbers;

import android.text.InputFilter;
import android.text.InputType;

public class FloatFragment extends NumberFragment {

    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }

}
