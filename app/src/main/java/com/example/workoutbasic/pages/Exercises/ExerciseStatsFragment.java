package com.example.workoutbasic.pages.Exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.workoutbasic.R;

import com.example.workoutbasic.pages.NavigationFragment;

public class ExerciseStatsFragment extends NavigationFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_stats, container, false);
    }
}
