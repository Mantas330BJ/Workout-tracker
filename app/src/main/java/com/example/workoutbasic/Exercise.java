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

    public LinearLayout getSetsLayout() {
        return setsLayout;
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

    public void removeSet(Context context) {
        ArrayList<SetData> setDatas = exerciseData.getSets();
        if (!setDatas.isEmpty()) {
            setDatas.remove(setDatas.size() - 1);
            setsLayout.removeViewAt(setDatas.size());
            size -= 100; //TODO: think about solution
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            params.height = size;
            textView.setLayoutParams(params);
        }
        else {
            Toast toast = Toast.makeText(context, context.getString(R.string.no_available_set), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
