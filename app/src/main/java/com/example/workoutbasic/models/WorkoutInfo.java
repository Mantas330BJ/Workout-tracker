package com.example.workoutbasic.models;

public class WorkoutInfo {
    private final String exercise;
    private final String sets;
    private final String formattedTopWeight;

    public WorkoutInfo(String exercise, String sets, String formattedTopWeight) { //TODO: looks fishy all strings. Maybe add real bindings
        this.exercise = exercise;
        this.sets = sets;
        this.formattedTopWeight = formattedTopWeight;
    }

    public String getExercise() {
        return exercise;
    }

    public String getSets() {
        return sets;
    }

    public String getFormattedTopWeight() {
        return formattedTopWeight;
    }
}
