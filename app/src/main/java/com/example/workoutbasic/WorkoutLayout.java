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

public class WorkoutLayout {
    private int size;
    private int sizeDebt = 0;
    private WorkoutLinearLayout dateLayout;
    private WorkoutLinearLayout exerciseLayout;
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
        dateLayout = new WorkoutLinearLayout(context);
        dateTextView = new WorkoutTextView(context);

        dateTextView.setGravity(Gravity.CENTER);
        dateTextView.setWidth(Data.columnWidths[0]);

        dateTextView.setBaseParams(workoutData.getDate());
        System.out.println(dateTextView.getText());
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

    public void setExerciseLayout(WorkoutLinearLayout exerciseLayout) {
        this.exerciseLayout = exerciseLayout;
    }

    public WorkoutLinearLayout getLayout() {
        return dateLayout;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSizeDebt() {
        return sizeDebt;
    }

    public void setSizeDebt(int sizeDebt) {
        this.sizeDebt = sizeDebt;
    }

    public void addExercise(ExerciseData exerciseData, Context context) {
        Exercise exercise = new Exercise(exerciseData, context);
        if (addExercise) {
            exercise.getLayout().setOnClickListener(v -> {
                ExerciseData copiedData = Data.copyExercise(exerciseData, 0);
                ((MainActivity) context).copyExercise(copiedData);
            });
        }
        if (sizeDebt > 0) {
            sizeDebt -= exercise.getSize();
        } else {
            size += Math.max(100, exercise.getSize());
        }
        ViewGroup.LayoutParams params = dateTextView.getLayoutParams();
        params.height = size;
        dateTextView.setLayoutParams(params);
        exerciseLayout.addView(exercise.getLayout());
    }
}
