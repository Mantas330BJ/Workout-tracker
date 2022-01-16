package com.example.workoutbasic.interfaces.input;

import android.view.View;

public interface WorkoutInput extends Inputs {
    void setTextClickListener();
    void setOnLongClickListener(View.OnLongClickListener onLongClickListener);
    void setOnClickListener(View.OnClickListener onClickListener);
}
