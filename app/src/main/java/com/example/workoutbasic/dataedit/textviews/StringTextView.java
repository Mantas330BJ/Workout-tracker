package com.example.workoutbasic.dataedit.textviews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;

@RequiresApi(api = Build.VERSION_CODES.O)

public class StringTextView extends WorkoutTextView {

    public StringTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        navController.navigate(R.id.action_editExerciseFragment_to_stringFragment);
    }
}
