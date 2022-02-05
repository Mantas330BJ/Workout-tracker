package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;

import com.example.workoutbasic.R;
import java.lang.Runnable;

import java.util.HashMap;
import java.util.Map;

public final class ListenerCreator {

    private ListenerCreator() {}

//    public static Runnable navigateToExerciseEditFragment(NavController navController) {
//        return () -> navController.navigate(R.id.action_editExerciseFragment_to_stringFragment);
//    }
    public static Runnable navigateToExerciseEditFragment(NavController navController) {
        return () -> navController.navigate(R.id.action_editExerciseFragment_to_stringFragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<Integer, Runnable> editTextMap(NavController navController) {
        Map<Integer, Runnable> map = new HashMap<>();
        map.put(R.id.weight, () -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.reps, () -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rir, () -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.restSeconds, () -> navController.navigate(R.id.action_editExerciseFragment_to_chooseRestFragment));
        map.put(R.id.comment, () -> navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment));
//        map.put(R.id.file, () -> navController.navigate(R.id.action_editExerciseFragment_to_integerFragment)); TODO: add proper navigation

        return map;
    }
}
