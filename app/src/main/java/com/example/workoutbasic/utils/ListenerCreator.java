package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.listeners.IntConsumer;

import java.util.HashMap;
import java.util.Map;

public final class ListenerCreator {

    private ListenerCreator() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<Integer, IntConsumer> editTextMap(NavController navController) {
        Map<Integer, IntConsumer> map = new HashMap<>();
        map.put(R.id.weight, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.reps, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rir, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rest, pos -> navController.navigate(R.id.action_editExerciseFragment_to_chooseRestFragment));
        map.put(R.id.comment, pos -> navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment));
//        map.put(R.id.file, pos -> navController.navigate(R.id.action_editExerciseFragment_to_integerFragment)); TODO: add proper navigation

        return map;
    }
}
