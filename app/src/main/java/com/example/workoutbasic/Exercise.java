package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Exercise {
    private WorkoutLinearLayout layout;
    private WorkoutLinearLayout setsLayout;
    private WorkoutTextView exerciseTextView;
    private Context context;
    private ExerciseData exerciseData;

    Exercise(ExerciseData exerciseData, Context context) {
        this.exerciseData = exerciseData;
        this.context = context;
        layout = new WorkoutLinearLayout(context);


        exerciseTextView = new WorkoutTextView(context);
        exerciseTextView.setGravity(Gravity.CENTER);
        exerciseTextView.setBaseParams(exerciseData.getExercise());
        layout.addView(exerciseTextView);
    }

    public ExerciseData getExerciseData() {
        return exerciseData;
    }

    public WorkoutTextView getExerciseTextView() {
        return exerciseTextView;
    }

    public WorkoutLinearLayout getLayout() {
        return layout;
    }
}
