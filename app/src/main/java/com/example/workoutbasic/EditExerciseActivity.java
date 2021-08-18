package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends DatabaseActivity implements OnInputListener {

    private WorkoutTextView currentClicked;
    private Exercise exercise;
    int workoutIdx;
    int exerciseIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);

        LinearLayout headers = findViewById(R.id.headers);
        headers.addView(Data.createColumnNames(this, 1));


        exercise = new Exercise(Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx), this, Data.EDIT); //TODO: pass exercise from previous activity??
        LinearLayout table = findViewById(R.id.table);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddSet(View view) {
        ArrayList<SetData> setDatas = exercise.getExerciseData().getSets();
        SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1), 1);
        setDatas.add(setData);
        exercise.addSet(setData, this, Data.EDIT);
    }

    public void onDeleteSet(View view) {
        exercise.removeSet(this);
    }
}