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
        linearLayoutManager.scrollToPosition(workoutIdx);
        Toast toast = Toast.makeText(context, context.getString(R.string.select_exercise), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void setListeners() {
        biIntConsumer = this::createDoubleClickListener;
        positionLongClickListener = this::createLongClickListener;
    }

    protected void createDoubleClickListener(int workoutIdx, int exerciseIdx, View view) {
        if (exerciseIdx != -1) { //Not header clicked
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.WORKOUT_IDX, workoutIdx);
            bundle.putInt(Constants.EXERCISE_IDX, exerciseIdx);
            bundle.putInt(Constants.DEST_WORKOUT_IDX, workoutIdx);

            navController.navigate(R.id.action_copyExerciseFragment_to_confirmExerciseFragment, bundle);
        }
    }
}
