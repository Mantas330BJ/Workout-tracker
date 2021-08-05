package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
@RequiresApi(api = Build.VERSION_CODES.O)

public class Workout {
    private Date date;
    private Exercise[] exercises;
    private LinearLayout layout;
    private LinearLayout exerciseLayout;
    private TextView textView;
    private int size = 0;

    Workout(Date date, Exercise[] exercises, Context context) {
        this.date = date;
        //this.exercises = exercises;
        layout = new LinearLayout(context);
        exerciseLayout = new LinearLayout(context);
        exerciseLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[0]);
        //textView.setLayoutParams(Data.getParams());

        Data.addText(textView, Data.getStringDate(date), context);
        layout.addView(textView);


        for (int i = 0; i < 3; ++i) {
            addExercise(context);
        }
        layout.addView(exerciseLayout);
    }

    public Date getDate() {
        return date;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public int getSize() {
        return size;
    }

    public void addExercise(Context context) {
        Exercise exercise = new Exercise("Broadas", new ArrayList<>(), context);
        size += exercise.getSize();
        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = size;
        textView.setLayoutParams(params);
        exerciseLayout.addView(exercise.getLayout());
    }
}
