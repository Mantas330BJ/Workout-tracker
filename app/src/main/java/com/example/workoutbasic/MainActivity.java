package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Build;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = findViewById(R.id.table_layout);
        TableRow columnRow = findViewById(R.id.first_row);
        fillRow(columnRow, Data.columnNames);

        for (WorkoutData workout : Data.workouts) {
            TableRow workoutRow = new TableRow(this);
            fillRow(workoutRow, workout.toStringArray());
            table.addView(workoutRow);
        }
    }

    public void fillRow(TableRow workoutRow, String[] columnNames) {
        for (String columnName : columnNames) {
            TextView columnRowText = new TextView(this);
            columnRowText.setTextSize(Data.textSize);
            columnRowText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.border_right_bottom, null));
            columnRowText.setText(columnName);
            workoutRow.addView(columnRowText);
        }
    }
}