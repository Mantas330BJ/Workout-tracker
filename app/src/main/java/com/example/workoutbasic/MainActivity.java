package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends AppCompatActivity implements OnInputListener {
    private TextView currentClicked; //TODO: Change this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = findViewById(R.id.table_layout);
        createColumnNames();

        for (int i = 0; i < 100; ++i) {
            for (WorkoutData workout : Data.workouts) {
                TableRow workoutRow = new TableRow(this);
                fillRow(workoutRow, workout.toStringArray());
                table.addView(workoutRow);
            }
        }
    }

    public void createColumnNames() {
        TableRow columnRow = findViewById(R.id.first_row);
        for (String columnName : Data.columnNames) {
            TextView columnRowText = new TextView(this);
            columnRowText.setTextSize(Data.textSize);
            columnRowText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.border_right_bottom, null));
            columnRowText.setText(columnName);
            columnRow.addView(columnRowText);
        }
    }

    public void fillRow(TableRow workoutRow, String[] columnNames) {
        for (String columnName : columnNames) {
            TextView columnRowText = new TextView(this);
            columnRowText.setOnClickListener((view) -> {
                currentClicked = columnRowText;
                Bundle bundle = new Bundle();
                bundle.putString(TextEditPopupFragment.EXTRA_STRING, columnRowText.getText().toString());
                TextEditPopupFragment popup = new TextEditPopupFragment();
                popup.setArguments(bundle);
                popup.show(getSupportFragmentManager(), "TextEditPopupFragment");
            });

            columnRowText.setTextSize(Data.textSize);
            columnRowText.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.border_right_bottom, null));
            columnRowText.setText(columnName);
            workoutRow.addView(columnRowText);
        }
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