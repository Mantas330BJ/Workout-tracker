package com.example.workoutbasic.utils;

import androidx.navigation.NavController;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.listeners.DoubleClickListener;
import com.example.workoutbasic.interfaces.listeners.OnClickListener;
import com.example.workoutbasic.models.ListenerMap;
import com.example.workoutbasic.viewadapters.sets.SetAdapter;

import java.util.HashMap;
import java.util.Map;

public final class ListenerCreator {

    public static ListenerMap openPageMap(DoubleClickListener listener, int exercisePosition) {
        Map<Integer, OnClickListener> map = new HashMap<>();
        for (int id : SetAdapter.ViewHolder.getIds()) {
            map.put(id, pos -> listener.onClick(exercisePosition).onClick(pos));
        }
        return new ListenerMap(map); //TODO: rewrite using streams Collections.toMap
    }

    public static ListenerMap editTextMap(NavController navController) {
        Map<Integer, OnClickListener> map = new HashMap<>();
        map.put(R.id.weight, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.reps, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rir, pos -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        map.put(R.id.rest, pos -> navController.navigate(R.id.action_editExerciseFragment_to_chooseRestFragment));
        map.put(R.id.comment, pos -> navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment));
//        map.put(R.id.file, pos -> navController.navigate(R.id.action_editExerciseFragment_to_integerFragment)); TODO: add proper navigation

        return new ListenerMap(map);
    }

    private ListenerCreator() {}
}
