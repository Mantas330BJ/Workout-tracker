package com.example.workoutbasic.pages.exercises;

import static com.example.workoutbasic.utils.WorkoutDataUtils.areExercises;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.FragmentEditWorkoutBinding;
import com.example.workoutbasic.interfaces.ButtonOptions;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.models.Workout;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.pages.dialogs.ChooseTypeFragment;
import com.example.workoutbasic.utils.DataRetriever;
import com.example.workoutbasic.utils.ListenerCreator;
import com.example.workoutbasic.viewadapters.exercises.TransitionalExerciseAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutFragment extends NavigationFragment implements ButtonOptions {
    private TransitionalExerciseAdapter transitionalExerciseAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int workoutIdx;
    private List<Exercise> exercises;
    private Workout workout;
    private Bundle adapterParams;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentEditWorkoutBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_workout, container, false);
        workout = getWorkoutData();
        adapterParams = new Bundle();
        adapterParams.putInt(Constants.WORKOUT_IDX, workoutIdx);
        binding.setWorkout(workout);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        setDate();

        exercises = workout.getExercises();
        transitionalExerciseAdapter = new TransitionalExerciseAdapter(exercises, adapterParams);

        transitionalExerciseAdapter.setClickListener(v ->
                navController.navigate(R.id.action_editWorkoutFragment_to_editExerciseFragment, adapterParams));
        transitionalExerciseAdapter.setLongClickListener(this::setLongClickListener);
        setExerciseButtonListener();
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.table);
        recyclerView.setAdapter(transitionalExerciseAdapter);

        linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        scrollScreen();
    }

    private Workout getWorkoutData() {
        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Constants.WORKOUT_IDX);
        return DataRetriever.getWorkoutDatas().get(workoutIdx);
    }

    private void setDate() {
        TextView date = view.findViewById(R.id.date);
        date.setOnClickListener(v -> ListenerCreator.navigateToDateEditFragment(navController));
    }

    private void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Constants.EXERCISE_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exercises.size() - 1 : scrollPosition);
    }

    private void createUndoSnackbar(int position, Exercise removedExercise) {
        Snackbar snackbar = Snackbar
                .make(((AppCompatActivity)context).findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.exercise)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    exercises.add(position, removedExercise);
                    linearLayoutManager.scrollToPosition(position);
                    transitionalExerciseAdapter.notifyItemInserted(position);
                    transitionalExerciseAdapter.notifyItemRangeChanged(position, exercises.size() - position);
                });
        snackbar.show();
    }

    private void setDoubleClickListener(View view) {
    }

    private void setLongClickListener(int position) {
        Exercise removedExercise = exercises.get(position);
        exercises.remove(position);
        transitionalExerciseAdapter.notifyItemRemoved(position);
        transitionalExerciseAdapter.notifyItemRangeChanged(position, exercises.size() - position);
        createUndoSnackbar(position, removedExercise);
    }

    private void setExerciseButtonListener() {
        Button exerciseButton = view.findViewById(R.id.exercise_button);
        exerciseButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(ChooseTypeFragment.NAME_KEY, context.getString(R.string.exercise));
            navController.navigate(R.id.action_editWorkoutFragment_to_chooseTypeFragment, bundle);
        });
    }

    @Override
    public void onCreateEmpty(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
        exercises.add(new Exercise());
        transitionalExerciseAdapter.notifyItemInserted(exercises.size() - 1);
        linearLayoutManager.scrollToPosition(exercises.size() - 1); //TODO: think about extracting
    }

    @Override
    public void onCreatePrevious(DialogFragment dialogFragment) {
        List<Workout> workoutList = DataRetriever.getWorkoutDatas();
        if (areExercises(workoutList)) {
            dialogFragment.dismiss();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.WORKOUT_IDX, workoutIdx);
            navController.navigate(R.id.action_chooseTypeFragment_to_copyExerciseFragment, bundle);
        } else {
            Toast.makeText(requireActivity(), getString(R.string.no_available, getString(R.string.exercise)), Toast.LENGTH_SHORT).show();
        }
    }
}