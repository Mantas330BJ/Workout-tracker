package com.example.workoutbasic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WorkoutImageView extends androidx.appcompat.widget.AppCompatImageView implements WorkoutInput {
    public WorkoutImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text) {

    }
}
