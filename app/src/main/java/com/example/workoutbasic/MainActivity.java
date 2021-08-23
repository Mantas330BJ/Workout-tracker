package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends DatabaseActivity {
    private ChooseTypeFragment currentFragment; //TODO: come on, is this really the best.
    private RecyclerView table;
    private LinearLayoutAdapter arrayAdapter;
    private int workoutIdx;
    private Button addWorkoutButton;
    private static boolean firstTime = true;

    private WorkoutData removedWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWorkoutButton = findViewById(R.id.workout_button);
        if (firstTime) {
            Data.initializeData(this);
            firstTime = false;
        }
        LinearLayout headers = findViewById(R.id.headers);
        headers.addView(Data.createColumnNames(this, 0));

        table = findViewById(R.id.table);

        boolean addExercise = shouldAddExercise();
        arrayAdapter = new LinearLayoutAdapter(Data.getWorkoutDatas(), addExercise);
        table.setAdapter(arrayAdapter);
        table.setLayoutManager(new LinearLayoutManager(this));


        arrayAdapter.setLongClickListener(position -> {
            ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
            removedWorkout = workoutDatas.get(position);
            workoutDatas.remove(position);
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, 1);
            Snackbar snackbar = Snackbar
                    .make(headers, getString(R.string.removed, getString(R.string.workout)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        workoutDatas.add(position, removedWorkout);
                        arrayAdapter.notifyItemInserted(position);
                        arrayAdapter.notifyItemRangeChanged(position, 1);
                    });
            snackbar.show();


        });

        if (shouldAddExercise()) {
            handleExercises();
        } else {
            setIntentClickListener();
        }
    }

    public boolean shouldAddExercise() {
        Object method = getIntent().getStringExtra(Data.METHOD);
        return method != null && method.equals("getExercise");
    }

    public LinearLayoutAdapter getArrayAdapter() {
        return arrayAdapter;
    }

    public void copyExercise(ExerciseData copiedData) {
        ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(workoutIdx).getExercises();
        destinationDatas.add(copiedData);
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra(Data.WORKOUT_IDX, workoutIdx); //TODO: put some position to scroll
        startActivity(intent);
    }

    public void handleExercises() {
        workoutIdx = (int) getIntent().getExtras().get(Data.WORKOUT_IDX);
        addWorkoutButton.setVisibility(View.GONE);
        Toast toast = Toast.makeText(this, getString(R.string.select_exercise), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onAddWorkout(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.PARENT, "workout"); //TODO: add resource probably
        currentFragment = new ChooseTypeFragment();
        currentFragment.setArguments(bundle);
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
    }

    public void setIntentClickListener() {
        arrayAdapter.setClickListener(position -> {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, position); //TODO: put some position to scroll
            startActivity(intent);
            finish();
        });
    }

    public void onCreateEmpty(View view) {
        ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
        workoutDatas.add(Data.createEmptyWorkout());
        setIntentClickListener();
        arrayAdapter.notifyItemInserted(workoutDatas.size() - 1);
        currentFragment.dismiss();
    }

    public void onCreatePrevious(View view) {
        currentFragment.dismiss();
        addWorkoutButton.setVisibility(View.GONE);
        Toast toast = Toast.makeText(this, getString(R.string.select_workout), Toast.LENGTH_SHORT);
        toast.show();
        arrayAdapter.setClickListener(position -> {
            ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
            WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(position), 0);
            workoutDatas.add(workoutData);
            arrayAdapter.notifyItemInserted(workoutDatas.size() - 1);
            addWorkoutButton.setVisibility(View.VISIBLE);
            setIntentClickListener();
        });
    }
}