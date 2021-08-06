package com.example.workoutbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditWorkoutActivity extends AppCompatActivity {
    public static final String WORKOUT_IDX = "widx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);
        int workoutIdx = (int)getIntent().getExtras().get(WORKOUT_IDX);
    }
}