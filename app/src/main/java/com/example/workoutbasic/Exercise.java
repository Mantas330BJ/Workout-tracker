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
    private LinearLayout layout;
    private LinearLayout setsLayout;
    private WorkoutTextView textView;
    private int size = 0;

    private ExerciseData exerciseData;


    Exercise(ExerciseData exerciseData, Context context, int mode) {
        this.exerciseData = exerciseData;
        layout = new LinearLayout(context);

        setsLayout = new LinearLayout(context);
        setsLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new WorkoutTextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[1]);
        textView.setParamsAndListener(exerciseData.getExercise(), mode);
        layout.addView(textView);
        //layout.setLayoutParams(Data.params);

        for (int i = 0; i < exerciseData.getSets().size(); ++i) {
            addSet(exerciseData.getSets().get(i), context, mode);
        }
        layout.addView(setsLayout);
    }

    public ExerciseData getExerciseData() {
        return exerciseData;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public int getSize() {
        return size;
    }

    public void addSet(SetData setData, Context context, int mode) {
        Set set = new Set(setData, context, mode);
        size += set.getSize();

        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = size;
        textView.setLayoutParams(params);

        setsLayout.addView(set.getLayout());
    }
}
