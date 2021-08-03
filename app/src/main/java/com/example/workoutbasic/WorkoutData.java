package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
@RequiresApi(api = Build.VERSION_CODES.O)


public class WorkoutData {
    private Date date;
    private String exercise;
    private int set;
    private float weight;
    private float RIR;
    private Duration rest;
    private String comment;

    WorkoutData(Date date, String exercise, int set, float weight,
                float RIR, Duration rest, String comment) {
        this.date = date;
        this.exercise = exercise;
        this.set = set;
        this.weight = weight;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public String getExercise() {
        return exercise;
    }

    public int getSet() {
        return set;
    }

    public String getStringSet() {
        return Integer.toString(set);
    }

    public float getWeight() {
        return weight;
    }

    public float getRIR() {
        return RIR;
    }

    public Duration getRest() {
        return rest;
    }

    public String getComment() {
        return comment;
    }

    public String[] toStringArray() {
        return new String[]{Data.getStringDate(date), exercise, Integer.toString(set), Data.getStringFloat(weight),
                Data.getStringFloat(RIR), Data.getStringDuration(rest), comment};
    }
}
