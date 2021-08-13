package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends AppCompatActivity implements OnInputListener {

    private WorkoutTextView currentClicked;
    private LinearLayout table;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        int workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        int exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);

        table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 1));

        exercise = new Exercise(Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx), this, Data.EDIT); //TODO: pass exercise from previous activity??
        table.addView(exercise.getLayout());
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }

    @Override
    public void setCurrentClicked(WorkoutTextView currentClicked) {
        this.currentClicked = currentClicked;
    }


    public void onAddSet(View view) {

    }

}