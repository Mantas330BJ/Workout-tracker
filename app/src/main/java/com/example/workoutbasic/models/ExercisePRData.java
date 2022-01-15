package com.example.workoutbasic.models;

import com.example.workoutbasic.variables.StringPasser;

public class ExercisePRData {
    private StringPasser exercise;

    public StringPasser getExercise() {
        return exercise;
    }

    public ExercisePRData(StringPasser exercise) {
        this.exercise = exercise;
    }
}
