package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class Exercise {

    private String exercise;
    private ArrayList<Set> sets;
    private LinearLayout layout;
    private LinearLayout setsLayout;
    private TextView textView;
    private int size = 0;

    Exercise(String exercise, ArrayList<Set> sets, Context context) {
        this.exercise = exercise;
        this.sets = sets;
        layout = new LinearLayout(context);
        setsLayout = new LinearLayout(context);
        setsLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[1]);
        Data.addText(textView, exercise, context);
        layout.addView(textView);
        //layout.setLayoutParams(Data.params);

        for (int i = 0; i < 5; ++i) {
            addSet(context);
        }
        layout.addView(setsLayout);
    }

    public String getExercise() {
        return exercise;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public int getSize() {
        return size;
    }

    public void addSet(Context context) {
        Set set = new Set(sets.size() + 1, 45, 4, Duration.ofMinutes(2), "Goot.", context);
        size += set.getSize();

        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = size;
        textView.setLayoutParams(params);
        System.out.println(size);

        sets.add(set);
        setsLayout.addView(set.getLayout());
    }
}
