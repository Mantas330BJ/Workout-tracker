package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.models.WorkoutData;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class WorkoutDataUtils {

    private WorkoutDataUtils() {
    }

    public static boolean areExercises(List<WorkoutData> workoutDataList) {
        return workoutDataList.stream()
                .map(WorkoutData::getExercises)
                .anyMatch(exercise -> !exercise.isEmpty());
    }
}
