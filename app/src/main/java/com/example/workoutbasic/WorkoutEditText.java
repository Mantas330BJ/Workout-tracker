package com.example.workoutbasic;

import android.content.Context;
import android.util.TypedValue;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class WorkoutEditText extends androidx.appcompat.widget.AppCompatEditText implements Editable {
    public WorkoutEditText(@NonNull Context context) {
        super(context);
        setDefaultParameters();
    }

    public void setDefaultParameters() {
        setEms(20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        params.setMargins(10, 10 ,10 ,10);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
    }
}
