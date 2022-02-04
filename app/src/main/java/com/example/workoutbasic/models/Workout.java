package com.example.workoutbasic.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)


public class Workout {
    private LocalDate workoutDate = LocalDate.now();
    private List<Exercise> exercises = new ArrayList<>();

    public Workout() {
    }

    public Workout(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Workout(Workout original) {
        this.workoutDate = original.getWorkoutDate();
        this.exercises = original.exercises.stream()
                .map(Exercise::new)
                .collect(Collectors.toList());
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
