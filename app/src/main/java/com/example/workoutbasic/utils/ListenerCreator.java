package com.example.workoutbasic.utils;

import androidx.navigation.NavController;

import com.example.workoutbasic.R;

public final class ListenerCreator {

    private ListenerCreator() {}

    public static void navigateToDateEditFragment(NavController navController) {
        navController.navigate(R.id.action_editWorkoutFragment_to_datePickFragment);
    }

    public static void navigateToExerciseEditFragment(NavController navController) {
        navController.navigate(R.id.action_editExerciseFragment_to_stringFragment);
    }
}
