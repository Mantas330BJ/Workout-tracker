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

public class Workout {
    private Stack<Integer> size;
    private LinearLayout layout;
    private LinearLayout exerciseLayout;

    private WorkoutTextView textView;


    private WorkoutData workoutData;

    Workout(WorkoutData workoutData, Context context, int mode) {
        size = new Stack<>();
        size.push(0);

        this.workoutData = workoutData;
        layout = new LinearLayout(context);
        exerciseLayout = new LinearLayout(context);
        exerciseLayout.setId(R.id.exercise_layout);

        exerciseLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new WorkoutTextView(context);

        textView.setGravity(Gravity.CENTER);
        textView.setWidth(Data.columnWidths[0]);

        textView.setParamsAndListener(workoutData.getDate(), mode == Data.EXERCISE ? Data.EDIT : mode);
        layout.addView(textView);


        //Extend class, call super until this??
        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            ExerciseData exerciseData = workoutData.getExercises().get(i);
            if (!exerciseData.getSets().isEmpty()) {
                addExercise(workoutData.getExercises().get(i), context, mode);
            } else {
                workoutData.getExercises().remove(i--);
            }
        }
        layout.addView(exerciseLayout);
    }


    public WorkoutData getWorkoutData() {
        return workoutData;
    }

    public LinearLayout getExerciseLayout() {
        return exerciseLayout;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public int getSize() {
        return size.peek();
    }

    public void addExercise(ExerciseData exerciseData, Context context, int mode) {
        Exercise exercise = new Exercise(exerciseData, context, mode);
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
