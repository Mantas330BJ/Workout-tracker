package com.example.workoutbasic.pages.exercises;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.pages.workouts.HistoryFragment;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CopyExerciseFragment extends HistoryFragment {
    private int workoutIdx; //Workout index from which exercise is being copied.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_copy_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Constants.WORKOUT_IDX);
        initializeView(view, savedInstanceState);
        Toast.makeText(context, context.getString(R.string.select_exercise), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getInitialScrollPosition() {
        return workoutIdx;
    }

    @Override
    public void setListeners() {
        biIntConsumer = this::confirmExercise;
        positionLongClickListener = this::removeWorkoutWithUndo;
    }

    protected void confirmExercise(View view) {
        if (adapterParams.get(Constants.EXERCISE_IDX) != null) //Not header clicked
            navController.navigate(R.id.action_copyExerciseFragment_to_confirmExerciseFragment, adapterParams);
    }
}
