package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends AppCompatActivity {
    private LinearLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data.initializeData();

        table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 0));


        for (int i = 0; i < Data.getWorkoutDatas().size(); ++i) {
            Workout workout = new Workout(Data.getWorkoutDatas().get(i), this, Data.WORKOUT);
            table.addView(workout.getLayout());
        }


    }

    public void onAddWorkout(View view) {
        ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
        WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(workoutDatas.size() - 1));
        workoutDatas.add(workoutData);
        Workout workout = new Workout(workoutData, this, Data.WORKOUT);
        table.addView(workout.getLayout());
    }


}