package com.example.workoutbasic.pages.dialogs;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.utils.DataRetriever;
import com.example.workoutbasic.viewadapters.exercises.TransitionalExerciseAdapter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ConfirmExerciseFragment extends DialogFragment {
    private NavController navController;
    private Bundle parentBundle;
    private Exercise exercise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.confirmation_fragment, container, false);
        navController = findNavController(this);

        assert getArguments() != null;
        parentBundle = getArguments();
        exercise = getExerciseData();

        setButtons(view);
        setRecyclerView(view);

        return view;
    }

    public void setRecyclerView(View view) {
        TransitionalExerciseAdapter transitionalExerciseAdapter = new TransitionalExerciseAdapter(Collections.singletonList(exercise), parentBundle); //TODO: check if still works
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(transitionalExerciseAdapter);
    }

    public void setButtons(View view) {
        Button yesButton = view.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(v -> confirmExercise(exercise));

        Button noButton = view.findViewById(R.id.no_button);
        noButton.setOnClickListener(v -> navController.popBackStack());
    }

    public Exercise getExerciseData() {
        int workoutIdx = parentBundle.getInt(Constants.WORKOUT_IDX);
        int exerciseIdx = parentBundle.getInt(Constants.EXERCISE_IDX);
        return DataRetriever.getWorkoutDatas().get(workoutIdx)
                .getExercises().get(exerciseIdx);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    public void confirmExercise(Exercise exercise) {

        int destWorkoutIdx = parentBundle.getInt(Constants.DEST_WORKOUT_IDX);

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.WORKOUT_IDX, destWorkoutIdx);

        List<Exercise> destinationDatas = DataRetriever.getWorkoutDatas()
                .get(destWorkoutIdx).getExercises();
        Exercise copiedExercise = new Exercise(exercise);
        destinationDatas.add(copiedExercise); //TODO: add adding methods to model classes?

        navController.navigate(R.id.action_confirmExerciseFragment_to_editWorkoutFragment, bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
