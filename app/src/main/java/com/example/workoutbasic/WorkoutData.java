package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)


public class WorkoutData extends Datas {
    private Dte date;
    private ArrayList<ExerciseData> exercises;

    WorkoutData(Dte date, ArrayList<ExerciseData> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    public Dte getDate() {
        return date;
    }

    public ArrayList<ExerciseData> getExercises() {
        return exercises;
    }

    public void setDate(Dte date) {
        this.date = date;
    }

    public void setExercises(ArrayList<ExerciseData> exercises) {
        this.exercises = exercises;
    }

}
