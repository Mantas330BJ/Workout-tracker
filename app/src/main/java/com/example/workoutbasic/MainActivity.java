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
    private ChooseTypeFragment currentFragment;
    private WorkoutAdapter arrayAdapter;
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
        arrayAdapter = new WorkoutAdapter(workoutDatas, addExercise);
        table.setAdapter(arrayAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        int workoutIdx = getIntent().getIntExtra(Data.WORKOUT_IDX, -1);
        linearLayoutManager.scrollToPosition(workoutIdx == -1 ? workoutDatas.size() - 1 : workoutIdx);

        if (shouldAddExercise()) {
            addWorkoutButton.setVisibility(View.GONE);
            Toast toast = Toast.makeText(this, getString(R.string.select_exercise), Toast.LENGTH_SHORT);
            toast.show();
            arrayAdapter.setSourceWorkoutIdx(workoutIdx);
        } else {
            setIntentClickListener();
        }
        table.setLayoutManager(linearLayoutManager);
    }

    public void setAdapterLongClickListener() {
        arrayAdapter.setLongClickListener(position -> {
            removedWorkout = workoutDatas.get(position);
            workoutDatas.remove(position);
            arrayAdapter.notifyDataSetChanged();
            //arrayAdapter.notifyItemRemoved(position);
            //arrayAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.workout)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        workoutDatas.add(position, removedWorkout);
                        arrayAdapter.notifyDataSetChanged();
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

    public LinearLayoutAdapter getArrayAdapter() {
        return arrayAdapter;
    }

    public void onAddWorkout(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.PARENT, getString(R.string.workout));
        currentFragment = new ChooseTypeFragment();
        currentFragment.setArguments(bundle);
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
    }

    public void setIntentClickListener() {
        arrayAdapter.setClickListener(position -> {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, position);
            startActivity(intent);
            finish();
        });
    }

    public void onCreateEmpty(View view) {
        workoutDatas.add(Data.createEmptyWorkout());
        setIntentClickListener();
        arrayAdapter.notifyItemInserted(workoutDatas.size() - 1);
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
            arrayAdapter.setClickListener(position -> {
                ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
                WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(position), 0);
                workoutDatas.add(workoutData);
                arrayAdapter.notifyItemInserted(workoutDatas.size() - 1);
                linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
                addWorkoutButton.setVisibility(View.VISIBLE);
                setIntentClickListener();
            });
        }
    }
}