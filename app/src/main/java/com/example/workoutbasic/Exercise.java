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

public class Exercise {
    private WorkoutLinearLayout layout;
    private WorkoutLinearLayout setsLayout;
    private WorkoutTextView exerciseTextView;
    private Context context;
    private int size = 0;

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

    public void initializeExerciseScreen() {
        exerciseTextView.setWidth(Data.columnWidths[1]);
        setsLayout = new WorkoutLinearLayout(context);
        setsLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < exerciseData.getSets().size(); ++i) {
            addSet(exerciseData.getSets().get(i), context);
        }
        getLayout().addView(setsLayout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(setsLayout.getLayoutParams());

        if (exerciseData.getSets().isEmpty()) {
            layoutParams.width = 0;
            for (int i = 2; i < Data.columnWidths.length; ++i) {
                layoutParams.width += Data.columnWidths[i];
            }
            setsLayout.setLayoutParams(layoutParams);
        }
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

    public WorkoutLinearLayout getSetsLayout() {
        return setsLayout;
    }

    public void setSetsLayout(WorkoutLinearLayout setsLayout) {
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
