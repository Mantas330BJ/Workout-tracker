package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout {
    private int size;
    private int sizeDebt = 0;
    private WorkoutLinearLayout dateLayout;
    private WorkoutLinearLayout exerciseLayout;
    private Context context;
    private WorkoutTextView dateTextView;
    private boolean addExercise;


    private WorkoutData workoutData;

    Workout(WorkoutData workoutData, Context context) {
        this.context = context;
        this.workoutData = workoutData;
    }

    public ArrayList<ArrayList<String>> getMainWorkoutInfo() {
        ArrayList<ArrayList<String>> strings = new ArrayList<>();
        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            ExerciseData exerciseData = workoutData.getExercises().get(i);
            String exercise = exerciseData.getExercise().toString();
            String sets = Integer.toString(exerciseData.getSets().size());

            float topWeight = 0;
            for (SetData setData : exerciseData.getSets()) {
                topWeight = Math.max(setData.getWeight().getFlt(), topWeight);
            }
            String formattedTopWeight = new Flt(topWeight).toString();

            strings.add(new ArrayList<>(Arrays.asList(exercise, sets, formattedTopWeight)));
        }


        /*
        if (workoutData.getExercises().isEmpty()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(exerciseLayout.getLayoutParams());
            layoutParams.width = 0;
            for (int i = 1; i < Data.columnWidths.length; ++i) {
                layoutParams.width += Data.columnWidths[i];
            }
            exerciseLayout.setLayoutParams(layoutParams);
        }
         */
        return strings;
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

    public void setAddExercise(boolean addExercise) {
        this.addExercise = addExercise;
    }

}
