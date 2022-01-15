package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.models.WorkoutInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.workoutbasic.models.SetData;
import com.example.workoutbasic.models.WorkoutData;
import com.example.workoutbasic.variables.DoublePasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutDisplayer {

    public static List<WorkoutInfo> extractMainWorkoutInfo(WorkoutData workoutData) {
        return workoutData.getExercises().stream().map(exerciseData -> {
            String exercise = exerciseData.getExercise().toString();
            String sets = Integer.toString(exerciseData.getSets().size());

            SetData maxSet = Collections.max(exerciseData.getSets(), Comparator
                    .comparingDouble((SetData s) -> s.getWeight().getDouble())
                    .thenComparingDouble(s -> s.getReps().getDouble()));

            String formattedTopWeight = new DoublePasser(maxSet.getReps().getDouble()).toString() +
                    " x " + new DoublePasser(maxSet.getWeight().getDouble()).toString();
            return new WorkoutInfo(exercise, sets, formattedTopWeight);
        }).collect(Collectors.toList());
    }
}
