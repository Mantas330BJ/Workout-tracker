package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Exercise extends ExerciseLayout {

    Exercise(ExerciseData exerciseData, Context context) {
        super(exerciseData, context);
        WorkoutLinearLayout setsLayout = new WorkoutLinearLayout(context);


        setSetsLayout(setsLayout);
        setsLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < exerciseData.getSets().size(); ++i) {
            addSet(exerciseData.getSets().get(i), context);
        }
        getLayout().addView(setsLayout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(setsLayout.getLayoutParams());

        if (exerciseData.getSets().isEmpty()) {
            layoutParams.width = 0;
            for (int i = 2; i < Data.columnWidths.length; ++i) {
                layoutParams.width += Data.columnWidths[i];
            }
            setsLayout.setLayoutParams(layoutParams);
        }
    }
}
