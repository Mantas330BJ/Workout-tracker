package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;

import androidx.annotation.RequiresApi;

import CustomViews.WorkoutLinearLayout;
import CustomViews.WorkoutTextView;
import Datas.ExerciseData;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Exercise {
    private WorkoutLinearLayout layout;
    private WorkoutLinearLayout setsLayout;
    private WorkoutTextView exerciseTextView;
    private Context context;
    private ExerciseData exerciseData;

    public Exercise(ExerciseData exerciseData, Context context) {
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
