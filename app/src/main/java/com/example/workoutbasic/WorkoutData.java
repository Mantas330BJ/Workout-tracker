package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
@RequiresApi(api = Build.VERSION_CODES.O)


public class WorkoutData {
    private Date date;
    private ArrayList<ExerciseData> exercises;

    WorkoutData(Date date, ArrayList<ExerciseData> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<ExerciseData> getExercises() {
        return exercises;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setExercises(ArrayList<ExerciseData> exercises) {
        this.exercises = exercises;
    }

}
