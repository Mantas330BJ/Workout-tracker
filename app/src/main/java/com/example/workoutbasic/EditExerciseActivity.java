package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends DatabaseActivity implements OnInputListener {
    private SetAdapter setAdapter;
    private Exercise exercise;

    private WorkoutInput currentClicked;
    int workoutIdx;
    int exerciseIdx;

    private SetData removedSet;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);
        ExerciseData exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
        WorkoutTextView exerciseName = findViewById(R.id.exercise);
        exerciseName.setText(exerciseData.getExercise().toString());

        exercise = new Exercise(exerciseData, this);
        exercise.getExerciseTextView().setTextEditListener();

        setAdapter = new SetAdapter(exerciseData.getSets());
        setAdapter.setShouldEdit(true);
        linearLayoutManager = new LinearLayoutManager(this);
        int scrollPosition = getIntent().getIntExtra(Data.SET_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exercise.getExerciseData().getSets().size() - 1 : scrollPosition);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        setAdapterLongClickListener();
    }


    public void setAdapterLongClickListener() {
        setAdapter.setLongClickListener(position -> {
            ArrayList<SetData> setDatas = exercise.getExerciseData().getSets();
            removedSet = setDatas.get(position);
            setDatas.remove(position);
            for (int i = position; i < setDatas.size(); ++i) {
                setDatas.get(i).setSet(new Int(i + 1));
            }
            setAdapter.notifyDataSetChanged();
            //arrayAdapter.notifyItemRemoved(position);
            //arrayAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        setDatas.add(position, removedSet);
                        for (int i = position; i < setDatas.size(); ++i) {
                            setDatas.get(i).setSet(new Int(i + 1));
                        }
                        linearLayoutManager.scrollToPosition(position);
                        setAdapter.notifyDataSetChanged();
                        //arrayAdapter.notifyItemInserted(position);
                        //arrayAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                    });
            snackbar.show();
        });
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }

    @Override
    public void setCurrentClicked(WorkoutInput currentClicked) {
        this.currentClicked = currentClicked;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddSet(View view) {
        ArrayList<SetData> setDatas = exercise.getExerciseData().getSets();
        if (!setDatas.isEmpty()) {
            SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1), 1);
            setDatas.add(setData);
        } else {
            setDatas.add(Data.createEmptySet());
        }
        setAdapter.notifyItemInserted(setDatas.size() - 1);
        linearLayoutManager.scrollToPosition(setDatas.size() - 1);
    }


}