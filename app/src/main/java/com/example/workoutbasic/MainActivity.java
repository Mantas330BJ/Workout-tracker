package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends AppCompatActivity {
    private RecyclerView table;
    LinearLayoutAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        if (extras == null)
            Data.initializeData(this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO: Store values to database
    }

    public LinearLayoutAdapter getArrayAdapter() {
        return arrayAdapter;
    }

    public void onAddWorkout(View view) {
        ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
        WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(workoutDatas.size() - 1));
        arrayAdapter.notifyItemInserted(workoutDatas.size());
        workoutDatas.add(workoutData);
    }


}