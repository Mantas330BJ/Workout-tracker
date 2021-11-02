package Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Datas.ExerciseData;
import Datas.ExercisePRData;
import Datas.WorkoutData;

public class ExerciseDisplayer {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<ExercisePRData> getExercises(ArrayList<WorkoutData> workoutDatas) {
        Set<ExercisePRData> exercises = new HashSet<>();
        for (WorkoutData data : workoutDatas) {
            for (ExerciseData exerciseData : data.getExercises()) {
                exercises.add(new ExercisePRData(exerciseData.getExercise()));
            }
        }
        return new ArrayList<>(exercises);
    }
}
