package com.example.workoutbasic.pages.sets;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.FragmentEditExerciseBinding;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.utils.DataRetriever;
import com.example.workoutbasic.viewadapters.exercises.EditableExerciseAdapter;
import com.example.workoutbasic.viewmodels.FileViewModel;
import com.example.workoutbasic.viewmodels.SetViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseFragment extends NavigationFragment {
    private EditableExerciseAdapter exerciseAdapter;
    private List<Set> sets;
    private Exercise exercise;
    private LinearLayoutManager linearLayoutManager;
    private Button setButton;

    private static final String PERMISSION_STRING = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final ActivityResultLauncher<String> permissionResult = getStringActivityResultLauncher();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentEditExerciseBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_exercise, container, false);
        requireActivity().getViewModelStore().clear();
        handlePermissions();
        exercise = getExerciseData();
        binding.setData(exercise);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); //TODO: think about listener stuff in exercise adapter
        sets = exercise.getSets();

        SetViewModel viewModel = new ViewModelProvider(requireActivity()).get(SetViewModel.class);

        exerciseAdapter = new EditableExerciseAdapter(Collections.singletonList(exercise), this);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exerciseAdapter);

        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        setButton = view.findViewById(R.id.set_button);

        scrollScreen();
        setListeners();
    }

    private void setListeners() {
        exerciseAdapter.setLongClickListener(this::createOnLongClickListener);
        setButton.setOnClickListener(this::copySetListener);
    }

    private void handlePermissions() {
        FileViewModel viewModel = new ViewModelProvider(requireActivity()).get(FileViewModel.class);
        viewModel.getSelected().observe(requireActivity(), text ->
                permissionResult.launch(PERMISSION_STRING));
    }

    private Exercise getExerciseData() {
        assert getArguments() != null;
        int workoutIdx = getArguments().getInt(Constants.WORKOUT_IDX);
        int exerciseIdx = getArguments().getInt(Constants.EXERCISE_IDX);
        return DataRetriever.getWorkoutDatas().get(workoutIdx)
                .getExercises().get(exerciseIdx);
    }

    private void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Constants.SET_IDX);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? sets.size() - 1 : scrollPosition);
    }

    private void createUndoSnackbar(int position, Set removedSet) {
        Snackbar.make(((AppCompatActivity) context).findViewById(android.R.id.content),
                getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    sets.add(position, removedSet);
                    DataRetriever.incrementSets(position, sets);
                    exerciseAdapter.notifyItemInserted(position);
                    exerciseAdapter.notifyItemRangeChanged(position, sets.size() - position);
                    linearLayoutManager.scrollToPosition(position);
                }).show();
    }

    private void copySetListener(View view) {
        if (!sets.isEmpty()) {
            Set set = new Set(sets.get(sets.size() - 1));
            set.setSetIdx(set.getSetIdx() + 1);
            sets.add(set);
        } else {
            sets.add(new Set());
        }
        exerciseAdapter.notifyItemInserted(sets.size() - 1);
        linearLayoutManager.scrollToPosition(sets.size() - 1);
    }

    private void createOnLongClickListener(int position) {
        Set removedSet = sets.get(position);
        sets.remove(position);
        DataRetriever.incrementSets(position, sets);

        exerciseAdapter.notifyItemRemoved(position);
        exerciseAdapter.notifyItemRangeChanged(position, sets.size() - position);
        createUndoSnackbar(position, removedSet);
    }

    private ActivityResultLauncher<String> getStringActivityResultLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                result -> {
                    if (result) {
                        navController.navigate(R.id.action_editExerciseFragment_to_chooseFileOptionsFragment);
                    } else {
                        Toast.makeText(getContext(), "Storage permission denied.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public View.OnLongClickListener getOnLongClickListener() {


    }
}