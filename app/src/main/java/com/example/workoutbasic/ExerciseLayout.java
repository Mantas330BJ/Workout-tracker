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

public class ExerciseLayout {
    private LinearLayout layout;
    private LinearLayout setsLayout;
    private WorkoutTextView exerciseTextView;
    private int size = 0;

    private ExerciseData exerciseData;


    ExerciseLayout(ExerciseData exerciseData, Context context) {
        this.exerciseData = exerciseData;
        layout = new LinearLayout(context);


        exerciseTextView = new WorkoutTextView(context);
        exerciseTextView.setGravity(Gravity.CENTER);
        exerciseTextView.setWidth(Data.columnWidths[1]);
        exerciseTextView.setBaseParams(exerciseData.getExercise());
        layout.addView(exerciseTextView);
    }

    public ExerciseData getExerciseData() {
        return exerciseData;
    }

    public WorkoutTextView getExerciseTextView() {
        return exerciseTextView;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public LinearLayout getSetsLayout() {
        return setsLayout;
    }

    public void setSetsLayout(LinearLayout setsLayout) {
        this.setsLayout = setsLayout;
    }

    public int getSize() {
        return size;
    }

    public void addSet(SetData setData, Context context) {
        Set set = new Set(setData, context, false);
        size += set.getSize();

        ViewGroup.LayoutParams params = exerciseTextView.getLayoutParams();
        params.height = size;
        exerciseTextView.setLayoutParams(params);

        setsLayout.addView(set.getLayout());
    }

    public void removeSet(Context context) {
        ArrayList<SetData> setDatas = exerciseData.getSets();
        if (!setDatas.isEmpty()) {
            setDatas.remove(setDatas.size() - 1);
            setsLayout.removeViewAt(setDatas.size());
            size -= 100; //TODO: think about solution
            ViewGroup.LayoutParams params = exerciseTextView.getLayoutParams();
            params.height = size;
            exerciseTextView.setLayoutParams(params);
        }
        else {
            Toast toast = Toast.makeText(context, context.getString(R.string.no_available_set), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
