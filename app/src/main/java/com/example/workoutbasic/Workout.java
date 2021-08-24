package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Stack;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout extends WorkoutLayout {

    Workout(WorkoutData workoutData, Context context, boolean addExercise) {
        super(workoutData, context, addExercise);
        WorkoutLinearLayout exerciseLayout = new WorkoutLinearLayout(context);
        setExerciseLayout(exerciseLayout);
        exerciseLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            addExercise(workoutData.getExercises().get(i), context);
        }
        getLayout().addView(exerciseLayout);


        if (workoutData.getExercises().isEmpty()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(exerciseLayout.getLayoutParams());
            layoutParams.width = 0;
            for (int i = 1; i < Data.columnWidths.length; ++i) {
                layoutParams.width += Data.columnWidths[i];
            }
            exerciseLayout.setLayoutParams(layoutParams);
        }
    }
}
