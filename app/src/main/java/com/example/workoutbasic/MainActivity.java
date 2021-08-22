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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends DatabaseActivity {
    private ChooseTypeFragment currentFragment; //TODO: come on, is this really the best.
    private RecyclerView table;
    private LinearLayoutAdapter arrayAdapter;
    private int workoutIdx;
    private static boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            workoutDatas.remove(position);
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, 1);
        });

        if (shouldAddExercise()) {
            handleExercises();
        } else {
            setIntentClickListener();
        }
    }

    public boolean shouldAddExercise() {
        boolean addExercise = false;
        Intent intentMain = getIntent();
        if (intentMain != null && intentMain.getExtras() != null) {
            Object data = intentMain.getExtras().get(Data.METHOD);
            if (data != null && ((String) data).equals("getExercise")) {
                addExercise = true;
            }
        }
        return addExercise;
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
        Toast toast = Toast.makeText(this, getString(R.string.select_workout), Toast.LENGTH_SHORT);
        toast.show();
        arrayAdapter.setClickListener(position -> {
            ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
            WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(position), 0);
            workoutDatas.add(workoutData);
            arrayAdapter.notifyItemInserted(workoutDatas.size() - 1);
            setIntentClickListener();
        });
    }
}