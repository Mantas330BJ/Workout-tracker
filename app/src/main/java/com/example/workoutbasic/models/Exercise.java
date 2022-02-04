package com.example.workoutbasic.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Exercise {
    private String exerciseName = "No exercise";
    private List<Set> sets = new ArrayList<>();

    public Exercise() {
    }

    public Exercise(String exerciseName, List<Set> sets) {
        this.exerciseName = exerciseName;
        this.sets = sets;
    }

    public Exercise(Exercise original) {
        this.exerciseName = original.exerciseName;
        this.sets = original.getSets().stream()
                .map(Set::new)
                .collect(Collectors.toList());
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}
