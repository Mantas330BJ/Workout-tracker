package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutActivity extends DatabaseActivity implements OnInputListener {
    private RecyclerView recyclerView;
    private LinearLayout table;
    private LinearLayoutAdapter arrayAdapter; //TODO: Add to parent activity???

    private WorkoutTextView currentClicked; //TODO: Change this
    public int workoutIdx;
    WorkoutLayout workoutLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        workoutLayout = new WorkoutLayout(Data.getWorkoutDatas().get(workoutIdx), this);
        workoutLayout.getDateTextView().setTextEditListener();


        LinearLayout date = new LinearLayout(this);
        date.setOrientation(LinearLayout.VERTICAL);
        date.addView(Data.createHeader(this, 0));
        date.addView(workoutLayout.getLayout());

        LinearLayout headers = new LinearLayout(this);
        headers.addView(Data.createColumnNames(this, 1));

        LinearLayout data = findViewById(R.id.data);
        data.addView(date);
        data.addView(headers);

        table = findViewById(R.id.table);
        //table.addView(workoutLayout.getLayout());

        arrayAdapter = new LinearLayoutAdapter(workoutLayout.getWorkoutData().getExercises());

        recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(arrayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayAdapter.setClickListener(exerciseIdx -> {
            Intent intent = new Intent(this, EditExerciseActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
            startActivity(intent);
            finish();
        });
        arrayAdapter.setLongClickListener(position -> {
            ArrayList<ExerciseData> exerciseDatas = workoutLayout.getWorkoutData().getExercises();
            exerciseDatas.remove(position);
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, 1);
        });
        table.addView(recyclerView);
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCurrentClicked(WorkoutTextView currentClicked) {
        this.currentClicked = currentClicked;
    }

    public void onAddExercise(View view) {
        ArrayList<ExerciseData> exerciseDatas = workoutLayout.getWorkoutData().getExercises();
        ExerciseData exerciseData = Data.copyExercise(exerciseDatas.get(exerciseDatas.size() - 1), 0);
        exerciseDatas.add(exerciseData);
        arrayAdapter.notifyItemInserted(exerciseDatas.size() - 1);
    }
}