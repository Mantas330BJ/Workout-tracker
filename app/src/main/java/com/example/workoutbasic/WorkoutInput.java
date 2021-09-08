package com.example.workoutbasic;

import android.view.View;

public interface WorkoutInput {
    void setText(CharSequence text);
    void setOnLongClickListener(View.OnLongClickListener onLongClickListener);
}
