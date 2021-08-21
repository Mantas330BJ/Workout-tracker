package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Stack;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout extends WorkoutLayout {

    Workout(WorkoutData workoutData, Context context) {
        super(workoutData, context);
        setExerciseLayout(new LinearLayout(context));
        getExerciseLayout().setId(R.id.exercise_layout);
        getExerciseLayout().setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            ExerciseData exerciseData = workoutData.getExercises().get(i);
            if (!exerciseData.getSets().isEmpty()) {
                addExercise(workoutData.getExercises().get(i), context);
            } else {
                workoutData.getExercises().remove(i--);
            }
        }
        getLayout().addView(getExerciseLayout());
    }
}
