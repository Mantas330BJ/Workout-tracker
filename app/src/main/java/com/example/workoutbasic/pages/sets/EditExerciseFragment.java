package com.example.workoutbasic.pages.sets;

import static com.example.workoutbasic.utils.Data.incrementSets;
import static com.example.workoutbasic.utils.ListenerCreator.editTextMap;

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

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.FragmentEditExerciseBinding;
import com.example.workoutbasic.dataedit.textviews.StringTextView;
import com.example.workoutbasic.interfaces.listeners.BiIntConsumer;
import com.example.workoutbasic.models.ExerciseData;
import com.example.workoutbasic.models.SetData;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.utils.Data;
import com.example.workoutbasic.utils.ListenerCreator;
import com.example.workoutbasic.utils.RecyclerViewConsumers;
import com.example.workoutbasic.variables.IntPasser;
import com.example.workoutbasic.viewadapters.sets.SetAdapter;
import com.example.workoutbasic.viewmodels.FileViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Optional;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseFragment extends NavigationFragment {
    private SetAdapter setAdapter;
    private ArrayList<SetData> setDatas;
    private ExerciseData exerciseData;
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
        exerciseData = getExerciseData();
        binding.setData(exerciseData);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View headers = view.findViewById(R.id.texts_layout);
        headers.setOnClickListener(this::createOnClickListener);
        setDatas = exerciseData.getSets();

        setAdapter = new SetAdapter(exerciseData.getSets());
        BiIntConsumer consumers = (id, pos) ->
                Optional.ofNullable(editTextMap(navController).get(id)).ifPresent(
                        intConsumer -> intConsumer.consume(pos)
                );
        setAdapter.setConsumers(consumers);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);

        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        setButton = view.findViewById(R.id.set_button);

        scrollScreen();
        setListeners();
    }

    private void setListeners() {
        setAdapter.setLongClickListener(this::createOnLongClickListener);
        setButton.setOnClickListener(this::createOnClickListener);
    }

    private void handlePermissions() {
        FileViewModel viewModel = new ViewModelProvider(requireActivity()).get(FileViewModel.class);
        viewModel.getSelected().observe(requireActivity(), text ->
                permissionResult.launch(PERMISSION_STRING));
    }

    private ExerciseData getExerciseData() {
        assert getArguments() != null;
        int workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        int exerciseIdx = getArguments().getInt(Data.EXERCISE_IDX);
        return Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
    }

    private void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Data.SET_IDX);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? setDatas.size() - 1 : scrollPosition);
    }

    private void createUndoSnackbar(int position, SetData removedSet) {
        Snackbar.make(((AppCompatActivity) context).findViewById(android.R.id.content),
                getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    setDatas.add(position, removedSet);
                    Data.incrementSets(position, setDatas);
                    linearLayoutManager.scrollToPosition(position);
                    setAdapter.notifyItemInserted(position);
                    setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                }).show();
    }

    private void createOnClickListener(View view) {
        if (!setDatas.isEmpty()) {
            SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1));
            setData.setSet(new IntPasser(setData.getSet().getVal() + 1));
            setDatas.add(setData);
        } else {
            setDatas.add(Data.createEmptySet());
        }
        setAdapter.notifyItemInserted(setDatas.size() - 1);
        linearLayoutManager.scrollToPosition(setDatas.size() - 1);
    }

    private void createOnLongClickListener(int position) {
        SetData removedSet = setDatas.get(position);
        setDatas.remove(position);
        incrementSets(position, setDatas);

        setAdapter.notifyItemRemoved(position);
        setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
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
}