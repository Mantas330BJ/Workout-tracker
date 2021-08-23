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

import com.google.android.material.snackbar.Snackbar;

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

    private SetData removedSet;

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
        //exercise.addView(Data.createHeader(this, 1));
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
            removedSet = setDatas.get(position);
            setDatas.remove(position);
            for (int i = position; i < setDatas.size(); ++i) {
                setDatas.get(i).setSet(new Int(i + 1));
            }
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
            Snackbar snackbar = Snackbar
                    .make(headers, getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        setDatas.add(position, removedSet);
                        for (int i = position; i < setDatas.size(); ++i) {
                            setDatas.get(i).setSet(new Int(i + 1));
                        }
                        arrayAdapter.notifyItemInserted(position);
                        arrayAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                    });
            snackbar.show();
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
        if (!setDatas.isEmpty()) {
            SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1), 1);
            setDatas.add(setData);
        } else {
            setDatas.add(Data.createEmptySet());
        }
        arrayAdapter.notifyItemInserted(setDatas.size() - 1);
    }


}