package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.models.ExercisePRData;
import com.example.workoutbasic.models.Workout;

public class ExerciseDisplayer {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<ExercisePRData> getExercises(List<Workout> workouts) {
        Set<ExercisePRData> exercises = new HashSet<>();
        for (Workout data : workouts) {
            for (Exercise exercise : data.getExercises()) {
                exercises.add(new ExercisePRData(exercise.getExerciseName()));
            }
        }
        return new ArrayList<>(exercises);
    }
}
