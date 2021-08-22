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

public class WorkoutLayout {
    private int size;
    private LinearLayout dateLayout;
    private LinearLayout exerciseLayout;
    private Context context;
    private WorkoutTextView dateTextView;
    private boolean addExercise;


    private WorkoutData workoutData;

    WorkoutLayout(WorkoutData workoutData, Context context, boolean addExercise) {
        this.context = context;
        this.workoutData = workoutData;
        this.addExercise = addExercise;
        addDate();

    }

    public void addDate() {
        dateLayout = new LinearLayout(context);
        dateTextView = new WorkoutTextView(context);

        dateTextView.setGravity(Gravity.CENTER);
        dateTextView.setWidth(Data.columnWidths[0]);

        dateTextView.setBaseParams(workoutData.getDate());
        dateLayout.addView(dateTextView);
    }

    public WorkoutTextView getDateTextView() {
        return dateTextView;
    }

    public WorkoutData getWorkoutData() {
        return workoutData;
    }

    public LinearLayout getExerciseLayout() {
        return exerciseLayout;
    }

    public void setExerciseLayout(LinearLayout exerciseLayout) {
        this.exerciseLayout = exerciseLayout;
    }

    public LinearLayout getLayout() {
        return dateLayout;
    }

    public int getSize() {
        return size;
    }

    public void addExercise(ExerciseData exerciseData, Context context) {
        Exercise exercise = new Exercise(exerciseData, context);
        if (addExercise) {
            exercise.getLayout().setOnClickListener(v -> {
                ExerciseData copiedData = Data.copyExercise(exerciseData, 0);
                ((MainActivity) context).copyExercise(copiedData);
            });
        }
        size += exercise.getSize();
        ViewGroup.LayoutParams params = dateTextView.getLayoutParams();
        params.height = size;
        dateTextView.setLayoutParams(params);
        exerciseLayout.addView(exercise.getLayout());
    }
}
