package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseLayout {
    private LinearLayout layout;
    private LinearLayout setsLayout;
    private WorkoutTextView exerciseTextView;
    private int size = 0;

    private ExerciseData exerciseData;


    ExerciseLayout(ExerciseData exerciseData, Context context) {
        this.exerciseData = exerciseData;
        layout = new LinearLayout(context);


        exerciseTextView = new WorkoutTextView(context);
        exerciseTextView.setGravity(Gravity.CENTER);
        exerciseTextView.setWidth(Data.columnWidths[1]); //TODO: change this??
        exerciseTextView.setBaseParams(exerciseData.getExercise());
        layout.addView(exerciseTextView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(exerciseTextView.getLayoutParams());
        params.setMargins(0, 0, 0, 0); //TODO: add some real params
        exerciseTextView.setLayoutParams(params);
    }

    public ExerciseData getExerciseData() {
        return exerciseData;
    }

    public WorkoutTextView getExerciseTextView() {
        return exerciseTextView;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public LinearLayout getSetsLayout() {
        return setsLayout;
    }

    public void setSetsLayout(LinearLayout setsLayout) {
        this.setsLayout = setsLayout;
    }

    public int getSize() {
        return size;
    }

    public void addSet(SetData setData, Context context) {
        Set set = new Set(setData, context, false);
        size += set.getSize();

        ViewGroup.LayoutParams params = exerciseTextView.getLayoutParams();
        params.height = size;
        exerciseTextView.setLayoutParams(params);

        setsLayout.addView(set.getLayout());
    }
}
