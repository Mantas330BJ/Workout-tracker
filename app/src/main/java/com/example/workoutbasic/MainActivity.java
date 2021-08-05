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


public class MainActivity extends AppCompatActivity implements OnInputListener {
    public static TextView currentClicked; //TODO: Change this
    private LinearLayout table;
    private LinearLayout row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = findViewById(R.id.table_grid);
        createColumnNames();



        for (int i = 0; i < 4; ++i) {
            //LinearLayout workoutRow = new LinearLayout(this);
            //workoutRow.setLayoutParams(Data.getParams());
            //fillRow(workoutRow, workout.toStringArray());
            Workout workout = new Workout(new Date(), null, this);
            table.addView(workout.getLayout());
        }


    }

    public void createColumnNames() {
        row = new LinearLayout(this);
        for (int i = 0; i < Data.columnNames.length; ++i) {
            String columnName = Data.columnNames[i];
            TextView columnRowText = new TextView(this);
            columnRowText.setWidth(Data.columnWidths[i]);

            columnRowText.setTextSize(Data.textSize);
            columnRowText.setTextAppearance(this, android.R.style.TextAppearance_Large);
            columnRowText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.border_right_bottom, null));
            Data.addText(columnRowText, columnName, this);
            row.addView(columnRowText);
            //columnRowText.setLayoutParams(Data.getParams());

        }
        table.addView(row);

    }




    @Override
    public void sendInput(String input) {
        currentClicked.setText(input);
    }

    public void onAddExercise(View view) {
    }

    public void onAddWorkout(View view) {
    }

    public void onAddSet(View view) {
    }


}