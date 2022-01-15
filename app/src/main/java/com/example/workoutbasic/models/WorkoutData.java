package com.example.workoutbasic.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import com.example.workoutbasic.variables.DatePasser;

@RequiresApi(api = Build.VERSION_CODES.O)


public class WorkoutData {
    private DatePasser date;
    private ArrayList<ExerciseData> exercises;

    public WorkoutData(DatePasser date, ArrayList<ExerciseData> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    public DatePasser getDate() {
        return date;
    }

    public void setDate(DatePasser date) {
        this.date = date;
    }

    public void setExercises(ArrayList<ExerciseData> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<ExerciseData> getExercises() {
        return exercises;
    }
}
