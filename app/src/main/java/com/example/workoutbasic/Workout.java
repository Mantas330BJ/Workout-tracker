package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout {
    private int size = 0;
    private final LinearLayout exerciseLayout;
    private final LinearLayout layout;

    private final WorkoutTextView textView;


    private final WorkoutData workoutData;

    Workout(WorkoutData workoutData, Context context, int mode) {
        this.workoutData = workoutData;
        layout = new LinearLayout(context);
        exerciseLayout = new LinearLayout(context);
        exerciseLayout.setId(R.id.exercise_layout);

        exerciseLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new WorkoutTextView(context);

        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[0]);

        textView.setParamsAndListener(workoutData.getDate(), mode == Data.EXERCISE ? Data.EDIT : mode);
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
