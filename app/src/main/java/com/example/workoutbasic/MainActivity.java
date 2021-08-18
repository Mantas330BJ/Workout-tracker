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
    private RecyclerView table;
    LinearLayoutAdapter arrayAdapter;
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

        arrayAdapter = new LinearLayoutAdapter(Data.getWorkoutDatas());
        table.setAdapter(arrayAdapter);
        table.setLayoutManager(new LinearLayoutManager(this));
        arrayAdapter.setListener(position -> {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, position); //TODO: put some position to scroll
            startActivity(intent);
            finish();
        });
    }

    public LinearLayoutAdapter getArrayAdapter() {
        return arrayAdapter;
    }

    public void onAddWorkout(View view) {
        ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
        System.out.println(workoutDatas.isEmpty() + " workoutdatas");
        if (workoutDatas.isEmpty()) {
            ArrayList<ExerciseData> exerciseDatas = new ArrayList<>();
            ArrayList<SetData> setDatas = new ArrayList<>();
            Data.addSetData(setDatas, 1, 0
                    , 0, 0, 0, "");
            Data.addExerciseData(exerciseDatas, "No exercise", setDatas);
            workoutDatas.add(new WorkoutData(new Dte(new Date()), exerciseDatas));
        } else {
            WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(workoutDatas.size() - 1), 0);
            workoutDatas.add(workoutData);
        }
        arrayAdapter.notifyItemInserted(workoutDatas.size() - 1);
    }


    public void onDeleteWorkout(View view) {
        ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
        if (!workoutDatas.isEmpty()) {
            workoutDatas.remove(workoutDatas.size() - 1);
            arrayAdapter.notifyItemRemoved(workoutDatas.size());
        }
        else {
            Toast toast = Toast.makeText(this, getString(R.string.no_available_workout), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}