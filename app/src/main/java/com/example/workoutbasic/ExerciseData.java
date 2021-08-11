package com.example.workoutbasic;

import android.widget.LinearLayout;

import java.util.ArrayList;

public class ExerciseData extends Datas {
    private Str exercise;
    private ArrayList<SetData> sets;

    ExerciseData(Str exercise, ArrayList<SetData> sets) {
        this.exercise = exercise;
        this.sets = sets;
    }

    public Str getExercise() {
        return exercise;
    }

    public ArrayList<SetData> getSets() {
        return sets;
    }

    public void setExercise(Str exercise) {
        this.exercise = exercise;
    }
}
