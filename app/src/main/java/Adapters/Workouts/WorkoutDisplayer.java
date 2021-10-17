package Adapters.Workouts;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

import Datas.ExerciseData;
import Datas.SetData;
import Datas.WorkoutData;
import Variables.DoublePasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutDisplayer {

    public static ArrayList<ArrayList<String>> getMainWorkoutInfo(WorkoutData workoutData) {
        ArrayList<ArrayList<String>> strings = new ArrayList<>();
        for (int i = 0; i < workoutData.getExercises().size(); ++i) {
            ExerciseData exerciseData = workoutData.getExercises().get(i);
            String exercise = exerciseData.getExercise().toString();
            String sets = Integer.toString(exerciseData.getSets().size());

            double topWeight = -1, reps = -1;

            for (SetData setData : exerciseData.getSets()) {
                double candidateWeight = setData.getWeight().getDouble();
                double candidateReps = setData.getReps().getDouble();
                if (candidateWeight > topWeight ||
                candidateWeight == topWeight && candidateReps > reps) {
                    topWeight = candidateWeight;
                    reps = candidateReps;
                }
            }
            String formattedTopWeight = new DoublePasser(reps).toString() + " x " + new DoublePasser(topWeight).toString();

            strings.add(new ArrayList<>(Arrays.asList(exercise, sets, formattedTopWeight)));
        }

        return strings;
    }
}
