package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutActivity extends AppCompatActivity implements OnInputListener {
    private WorkoutTextView currentClicked; //TODO: Change this
    public int workoutIdx;
    Workout workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        int workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX) - 1; //Headers are in the first row.
        LinearLayout table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 0));

        System.out.println("Remove initialize data below" + Data.getWorkoutDatas());
        //Data.initializeData();
        workout = new Workout(Data.getWorkoutDatas().get(workoutIdx), this, Data.EXERCISE);
        table.addView(workout.getLayout());
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCurrentClicked(WorkoutTextView currentClicked) {
        this.currentClicked = currentClicked;
    }

    public void onAddExercise(View view) {
        ArrayList<ExerciseData> exerciseDatas = workout.getWorkoutData().getExercises();
        ExerciseData exerciseData = Data.copyExercise(exerciseDatas.get(exerciseDatas.size() - 1));
        exerciseDatas.add(exerciseData);
        workout.addExercise(exerciseData, this, Data.EXERCISE);
    }
}