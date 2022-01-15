package com.example.workoutbasic.models;

public class WorkoutInfo {
    private final String exercise;
    private final String sets;
    private final String formattedTopWeight; //TODO: improve binding or something

    public WorkoutInfo(String exercise, String sets, String formattedTopWeight) {
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
