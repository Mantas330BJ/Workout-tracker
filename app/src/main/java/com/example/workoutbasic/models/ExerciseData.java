package com.example.workoutbasic.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import com.example.workoutbasic.variables.StringPasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseData {
    private StringPasser exercise;
    private final ArrayList<SetData> sets;

    public ExerciseData(StringPasser exercise, ArrayList<SetData> sets) {
        this.exercise = exercise;
        this.sets = sets;
    }

    public StringPasser getExercise() {
        return exercise;
    }

    public ArrayList<SetData> getSets() {
        return sets;
    }

    public void setExercise(StringPasser exercise) {
        this.exercise = exercise;
    }
}
