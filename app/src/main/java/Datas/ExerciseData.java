package Datas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import Datas.SetData;
import Variables.Str;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseData {
    private Str exercise;
    private final ArrayList<SetData> sets;

    public ExerciseData(Str exercise, ArrayList<SetData> sets) {
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
