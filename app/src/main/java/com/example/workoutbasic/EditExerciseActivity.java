package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
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

public class EditExerciseActivity extends DatabaseActivity implements OnInputListener {
    private RecyclerView recyclerView;
    private LinearLayout table;
    private LinearLayoutAdapter arrayAdapter;

    private WorkoutTextView currentClicked;
    private ExerciseLayout exerciseLayout;
    int workoutIdx;
    int exerciseIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);
        exerciseLayout = new ExerciseLayout(Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx), this);
        exerciseLayout.getExerciseTextView().setTextEditListener();

        LinearLayout exercise = new LinearLayout(this); //TODO: put everything in one function to call from each activity
        exercise.setOrientation(LinearLayout.VERTICAL);
        exercise.addView(Data.createHeader(this, 1));
        exercise.addView(exerciseLayout.getLayout());

        LinearLayout headers = new LinearLayout(this);
        headers.addView(Data.createColumnNames(this, 2));

        LinearLayout data = findViewById(R.id.data);
        data.addView(exercise);
        data.addView(headers);


        table = findViewById(R.id.table);
        arrayAdapter = new LinearLayoutAdapter(exerciseLayout.getExerciseData().getSets());


        recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(arrayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        table.addView(recyclerView);

        arrayAdapter.setLongClickListener(position -> {
            ArrayList<SetData> setDatas = exerciseLayout.getExerciseData().getSets();
            setDatas.remove(position);
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, 1);
        });

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
        ArrayList<SetData> setDatas = exerciseLayout.getExerciseData().getSets();
        SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1), 1);
        setDatas.add(setData);
        arrayAdapter.notifyItemInserted(setDatas.size() - 1);
    }
}