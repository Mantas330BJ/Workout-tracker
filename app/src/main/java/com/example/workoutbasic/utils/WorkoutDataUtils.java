package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.models.Workout;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class WorkoutDataUtils {

    private WorkoutDataUtils() {
    }

    public static boolean areExercises(List<Workout> workoutList) {
        return workoutList.stream()
                .map(Workout::getExercises)
                .anyMatch(exercise -> !exercise.isEmpty());
    }
}
