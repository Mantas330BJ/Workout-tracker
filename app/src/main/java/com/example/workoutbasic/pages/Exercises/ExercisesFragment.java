package com.example.workoutbasic.pages.Exercises;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;

import com.example.workoutbasic.adapters.Exercises.ExercisePRAdapter;
import com.example.workoutbasic.models.ExercisePRData;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.utils.ExerciseDisplayer;

public class ExercisesFragment extends NavigationFragment {
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ExercisePRData> exercises = ExerciseDisplayer.getExercises(Data.getWorkoutDatas());
        ExercisePRAdapter workoutInfoAdapter = new ExercisePRAdapter(exercises);
        workoutInfoAdapter.setOnClickListener(v ->
                navController.navigate(R.id.action_exercisesFragment_to_exerciseStatsFragment));

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(workoutInfoAdapter);

        linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

    }
}
