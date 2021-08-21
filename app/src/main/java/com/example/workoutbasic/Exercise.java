package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Exercise extends ExerciseLayout {

    Exercise(ExerciseData exerciseData, Context context) {
        super(exerciseData, context);
        setSetsLayout(new LinearLayout(context));
        getSetsLayout().setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < exerciseData.getSets().size(); ++i) {
            addSet(exerciseData.getSets().get(i), context);
        }
        getLayout().addView(getSetsLayout());
    }
}
