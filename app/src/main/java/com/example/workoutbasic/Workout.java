package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

import Datas.ExerciseData;
import Datas.SetData;
import Datas.WorkoutData;
import Variables.Flt;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout {
    private WorkoutData workoutData;

    public Workout(WorkoutData workoutData) {
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

}
