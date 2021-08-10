package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends AppCompatActivity implements OnInputListener {

    public WorkoutTextView currentClicked; //TODO: Change this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        int workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        int exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);

        LinearLayout table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 1));

        Exercise exercise = new Exercise(Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx), this, 2);
        table.addView(exercise.getLayout());
    }

    @Override
    public void sendInput(String input) {
        currentClicked.setText(input);
        Object parentData = currentClicked.getParentData();
        switch (currentClicked.getDepth()) {
            case Data.WORKOUT_DEPTH:
                //((WorkoutData)parentData).setDate(input);
                break;
            case Data.EXERCISE_DEPTH:
                ((ExerciseData)parentData).setExercise(new Str(input)); //index matters
                break;
            case Data.SET_DEPTH:
                int idx = ((ViewGroup)currentClicked.getParent()).indexOfChild(currentClicked);
                SetData parent = (SetData)parentData;
                switch (idx) {
                    case 0:
                        //parent.setSet()
                        break;
                    case 1:
                        //parent.setWeight()
                        break;
                    case 2:
                        //parent.setRIR()
                        break;
                    case 3:
                        //parent.setRest()
                        break;
                    case 4:
                        parent.setComment(new Str(input));
                        break;
                }
        }
    }

    public void onAddSet(View view) {
    }

}