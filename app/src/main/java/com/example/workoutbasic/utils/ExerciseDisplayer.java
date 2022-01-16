package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.workoutbasic.models.ExerciseData;
import com.example.workoutbasic.models.ExercisePRData;
import com.example.workoutbasic.models.WorkoutData;

public class ExerciseDisplayer {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<ExercisePRData> getExercises(List<WorkoutData> workoutDatas) {
        Set<ExercisePRData> exercises = new HashSet<>();
        for (WorkoutData data : workoutDatas) {
            for (ExerciseData exerciseData : data.getExercises()) {
                exercises.add(new ExercisePRData(exerciseData.getExercise()));
            }
        }
        return new ArrayList<>(exercises);
    }
}
