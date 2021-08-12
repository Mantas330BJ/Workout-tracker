package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout {
    private int size = 0;
    private int depth = Data.WORKOUT_DEPTH;
    private LinearLayout exerciseLayout;
    private LinearLayout layout;

    private WorkoutTextView textView;


    private WorkoutData workoutData;

    Workout(WorkoutData workoutData, Context context, int mode) {
        this.workoutData = workoutData;
        layout = new LinearLayout(context);
        exerciseLayout = new LinearLayout(context);
        exerciseLayout.setId(R.id.exercise_layout);

        exerciseLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new WorkoutTextView(context);

        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[0]);

        textView.setParamsAndListener(workoutData.getDate(), mode != 1 ? 2 : mode);
        layout.addView(textView);


        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            addExercise(workoutData.getExercises().get(i), context, mode);
        }
        layout.addView(exerciseLayout);
    }


    public WorkoutData getWorkoutData() {
        return workoutData;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public int getSize() {
        return size;
    }

    public void addExercise(ExerciseData exerciseData, Context context, int mode) {
        Exercise exercise = new Exercise(exerciseData, context, mode);
        size += exercise.getSize();
        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = size;
        textView.setLayoutParams(params);
        exerciseLayout.addView(exercise.getLayout());
    }
}
