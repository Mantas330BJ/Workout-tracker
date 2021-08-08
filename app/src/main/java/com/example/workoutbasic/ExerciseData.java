package com.example.workoutbasic;

import android.widget.LinearLayout;

import java.util.ArrayList;

public class ExerciseData {
    private String exercise;
    private ArrayList<SetData> sets;

    ExerciseData(String exercise, ArrayList<SetData> sets) {
        this.exercise = exercise;
        this.sets = sets;
    }

    public String getExercise() {
        return exercise;
    }

    public ArrayList<SetData> getSets() {
        return sets;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
