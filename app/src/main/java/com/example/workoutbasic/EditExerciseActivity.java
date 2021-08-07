package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends AppCompatActivity implements OnInputListener {

    public TextView currentClicked; //TODO: Change this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        int workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        int exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);

        LinearLayout table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 1));

        Exercise exercise = new Exercise(Data.workoutDatas.get(workoutIdx).getExercises().get(exerciseIdx), this, 2);
        table.addView(exercise.getLayout());
    }

    @Override
    public void sendInput(String input) {
        currentClicked.setText(input);
    }

    public void onAddSet(View view) {
    }

}