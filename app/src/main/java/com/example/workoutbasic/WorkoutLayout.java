package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Stack;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutLayout {
    private Stack<Integer> size;
    private LinearLayout dateLayout;
    private LinearLayout exerciseLayout;
    private Context context;
    private WorkoutTextView textView;


    private WorkoutData workoutData;

    WorkoutLayout(WorkoutData workoutData, Context context) {
        size = new Stack<>();
        size.push(0);
        this.context = context;
        this.workoutData = workoutData;
        addDate();

    }

    public void addDate() {
        dateLayout = new LinearLayout(context);
        textView = new WorkoutTextView(context);

        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[0]);

        textView.setBaseParams(workoutData.getDate());
        dateLayout.addView(textView);
    }


    public WorkoutData getWorkoutData() {
        return workoutData;
    }

    public LinearLayout getExerciseLayout() {
        return exerciseLayout;
    }

    public void setExerciseLayout(LinearLayout exerciseLayout) {
        this.exerciseLayout = exerciseLayout;
    }

    public LinearLayout getLayout() {
        return dateLayout;
    }

    public int getSize() {
        return size.peek();
    }

    public void addExercise(ExerciseData exerciseData, Context context) {
        Exercise exercise = new Exercise(exerciseData, context);
        int lastSize = size.peek();
        size.push(exercise.getSize() + lastSize);
        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = size.peek();
        textView.setLayoutParams(params);
        exerciseLayout.addView(exercise.getLayout());
    }

    public void removeExercise(Context context) {
        ArrayList<ExerciseData> exerciseDatas = workoutData.getExercises();
        if (!exerciseDatas.isEmpty()) {
            exerciseDatas.remove(exerciseDatas.size() - 1);
            exerciseLayout.removeViewAt(exerciseDatas.size());
            size.pop();
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            params.height = size.peek();
            textView.setLayoutParams(params);
        }
        else {
            Toast toast = Toast.makeText(context, context.getString(R.string.no_available_exercise), Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}