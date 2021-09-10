package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends DatabaseActivity {
    private ChooseTypeFragment currentFragment;
    private WorkoutAdapter workoutAdapter;
    private Button addWorkoutButton;
    private static boolean firstTime = true;
    private ArrayList<WorkoutData> workoutDatas;

    private WorkoutData removedWorkout;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWorkoutButton = findViewById(R.id.workout_button);
        if (firstTime) {
            Data.initializeData(this);
            firstTime = false;
        }

        workoutDatas = Data.getWorkoutDatas();
        createAdapter();
        setAdapterLongClickListener();
    }

    public void createAdapter() {
        RecyclerView table = findViewById(R.id.table);
        boolean addExercise = shouldAddExercise();
        workoutAdapter = new WorkoutAdapter(workoutDatas, addExercise);
        table.setAdapter(workoutAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        int workoutIdx = getIntent().getIntExtra(Data.WORKOUT_IDX, -1);
        linearLayoutManager.scrollToPosition(workoutIdx == -1 ? workoutDatas.size() - 1 : workoutIdx);

        if (shouldAddExercise()) {
            addWorkoutButton.setVisibility(View.GONE);
            Toast toast = Toast.makeText(this, getString(R.string.select_exercise), Toast.LENGTH_SHORT);
            toast.show();
            workoutAdapter.setWorkoutListenerClickListener(position -> childPos -> {
                Intent intent = new Intent(this, EditWorkoutActivity.class);
                intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
                ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(workoutIdx).getExercises();
                destinationDatas.add(Data.getWorkoutDatas().get(position).getExercises().get(childPos));
                startActivity(intent);
            });
        } else {
            setIntentClickListener();
        }
        table.setLayoutManager(linearLayoutManager);
    }

    public void setAdapterLongClickListener() {
        workoutAdapter.setLongClickListener(position -> { //childPosition does not matter
            removedWorkout = workoutDatas.get(position);
            workoutDatas.remove(position);
            workoutAdapter.notifyDataSetChanged();
            //arrayAdapter.notifyItemRemoved(position);
            //arrayAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.workout)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        workoutDatas.add(position, removedWorkout);
                        workoutAdapter.notifyDataSetChanged();
                        //arrayAdapter.notifyItemInserted(position);
                        //arrayAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
                        linearLayoutManager.scrollToPosition(position);

                    });
            snackbar.show();
        });
    }

    public boolean shouldAddExercise() {
        Object method = getIntent().getStringExtra(Data.METHOD);
        return method != null && method.equals("getExercise");
    }

    public LinearLayoutAdapter getWorkoutAdapter() {
        return workoutAdapter;
    }

    public void onAddWorkout(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.PARENT, getString(R.string.workout));
        currentFragment = new ChooseTypeFragment();
        currentFragment.setArguments(bundle);
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
    }

    public void setIntentClickListener() {
        workoutAdapter.setWorkoutListenerClickListener(position -> childPos -> {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, position);
            if (childPos != -1)
                intent.putExtra(Data.EXERCISE_IDX, childPos);
            startActivity(intent);
        });
    }

    public void onCreateEmpty(View view) {
        workoutDatas.add(Data.createEmptyWorkout());
        setIntentClickListener();
        workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
        linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
        currentFragment.dismiss();
    }

    public void onCreatePrevious(View view) {
        if (workoutDatas.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_available, getString(R.string.workout)), Toast.LENGTH_SHORT).show();
        } else {
            currentFragment.dismiss();
            addWorkoutButton.setVisibility(View.GONE);
            Toast toast = Toast.makeText(this, getString(R.string.select_workout), Toast.LENGTH_SHORT);
            toast.show();

            workoutAdapter.setWorkoutListenerClickListener(position -> childPosition -> {
                ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
                WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(position), 0);
                workoutDatas.add(workoutData);
                workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
                linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
                addWorkoutButton.setVisibility(View.VISIBLE);
                setIntentClickListener();
            });
            //workoutAdapter.setClickListener(workoutAdapter.getWorkoutListenerClickListener().onClick(0));
        }
    }
}