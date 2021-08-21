package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseData extends Datas {
    private Str exercise;
    private final ArrayList<SetData> sets;

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


    @Override
    public boolean emptyChildren() {
        return sets.isEmpty();
    }

    @Override
    public LinearLayout getLayout(Context context) {
        return new Exercise(this, context).getLayout();
    }
}
