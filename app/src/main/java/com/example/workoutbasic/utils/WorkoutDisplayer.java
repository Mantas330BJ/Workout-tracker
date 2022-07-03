package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.models.Workout;
import com.example.workoutbasic.models.WorkoutInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutDisplayer {

    public static List<WorkoutInfo> extractMainWorkoutInfo(Workout workout) {
        return workout.getExercises().stream()
                .map(WorkoutDisplayer::toWorkoutInfo)
                .collect(Collectors.toList());
    }

    @NonNull
    private static WorkoutInfo toWorkoutInfo(Exercise exerciseData) {
        String exercise = exerciseData.getExerciseName();
        String sets = Integer.toString(exerciseData.getSets().size());

        Set maxSet = Collections.max(exerciseData.getSets(), Comparator
                .comparingDouble(Set::getWeight)
                .thenComparingDouble(Set::getReps));

        String formattedTopWeight = String.format(Locale.getDefault(),
                "%s x %s",
                StringConverter.convertDouble(maxSet.getReps()),
                StringConverter.convertDouble(maxSet.getWeight()));
        return new WorkoutInfo(exercise, sets, formattedTopWeight);
    }
}
