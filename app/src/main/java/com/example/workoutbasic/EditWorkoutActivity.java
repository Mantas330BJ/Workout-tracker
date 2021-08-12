package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutActivity extends AppCompatActivity implements OnInputListener {
    private WorkoutTextView currentClicked; //TODO: Change this
    public int workoutIdx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);
        int workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX) - 1; //Headers are in the first row.
        LinearLayout table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 0));

        System.out.println("Remove initialize data " + Data.getWorkoutDatas());
        Data.initializeData();
        Workout workout = new Workout(Data.getWorkoutDatas().get(workoutIdx), this, 0);
        table.addView(workout.getLayout());
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }

    public void onAddExercise(View view) {
    }

    @Override
    public void setCurrentClicked(WorkoutTextView currentClicked) {
        this.currentClicked = currentClicked;
    }
}