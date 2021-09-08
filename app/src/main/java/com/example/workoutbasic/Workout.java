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
    private boolean addExercise; //TODO: look why never accessed

    private WorkoutData workoutData;

    Workout(WorkoutData workoutData) {
        this.workoutData = workoutData;
    }

    public ArrayList<ArrayList<String>> getMainWorkoutInfo() {
        ArrayList<ArrayList<String>> strings = new ArrayList<>();
        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            ExerciseData exerciseData = workoutData.getExercises().get(i);
            String exercise = exerciseData.getExercise().toString();
            String sets = Integer.toString(exerciseData.getSets().size());

            float topWeight = -1, reps = -1;

            for (SetData setData : exerciseData.getSets()) {
                float candidateWeight = setData.getWeight().getFlt();
                float candidateReps = setData.getReps().getFlt();
                if (candidateWeight > topWeight ||
                candidateWeight == topWeight && candidateReps > reps) {
                    topWeight = candidateWeight;
                    reps = candidateReps;
                }
            }
            String formattedTopWeight = new Flt(reps).toString() + " x " + new Flt(topWeight).toString();

            strings.add(new ArrayList<>(Arrays.asList(exercise, sets, formattedTopWeight)));
        }

        return strings;
    }

    public WorkoutData getWorkoutData() {
        return workoutData;
    }

    public void setAddExercise(boolean addExercise) {
        this.addExercise = addExercise;
    }

}
