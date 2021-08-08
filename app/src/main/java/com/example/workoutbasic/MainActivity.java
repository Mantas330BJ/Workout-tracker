package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends AppCompatActivity {
    private LinearLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Passed one function");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Data.initializeData();

        table = findViewById(R.id.table);
        table.addView(Data.createColumnNames(this, 0));


        for (int i = 0; i < Data.workoutDatas.size(); ++i) {
            Workout workout = new Workout(Data.workoutDatas.get(i), this, 1);
            table.addView(workout.getLayout());
        }


    }

    public void onAddWorkout(View view) {
    }


}