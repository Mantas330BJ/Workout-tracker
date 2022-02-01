package com.example.workoutbasic.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.listeners.BiPositionListener;
import com.example.workoutbasic.interfaces.listeners.PositionListener;
import com.example.workoutbasic.viewadapters.sets.SetAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ListenerCreator {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<Integer, PositionListener> openPageMap(BiPositionListener biListener, int exercisePosition) {
        PositionListener listener = pos -> biListener.onClick(exercisePosition, pos); //peels one layer

        return SetAdapter.ViewHolder.getIds().stream()
                .collect(Collectors.toMap(Function.identity(), id -> listener)
        );
    }

    public static Map<Integer, PositionListener> editTextMap(NavController navController) {
        Map<Integer, PositionListener> map = new HashMap<>();
        map.put(R.id.weight, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.reps, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rir, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rest, pos -> navController.navigate(R.id.action_editExerciseFragment_to_chooseRestFragment));
        map.put(R.id.comment, pos -> navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment));
//        map.put(R.id.file, pos -> navController.navigate(R.id.action_editExerciseFragment_to_integerFragment)); TODO: add proper navigation

        return map;
    }

    private ListenerCreator() {}
}
