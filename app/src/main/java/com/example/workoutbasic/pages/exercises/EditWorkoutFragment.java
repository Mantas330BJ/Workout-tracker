package com.example.workoutbasic.pages.exercises;

import static com.example.workoutbasic.utils.WorkoutDataUtils.areExercises;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.FragmentEditWorkoutBinding;
import com.example.workoutbasic.dataedit.textviews.DatePickTextView;
import com.example.workoutbasic.interfaces.ButtonOptions;
import com.example.workoutbasic.interfaces.listeners.BiPositionListener;
import com.example.workoutbasic.models.ExerciseData;
import com.example.workoutbasic.models.WorkoutData;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.pages.dialogs.ChooseTypeFragment;
import com.example.workoutbasic.utils.Data;
import com.example.workoutbasic.utils.ListenerCreator;
import com.example.workoutbasic.viewadapters.exercises.ExerciseAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutFragment extends NavigationFragment implements ButtonOptions {
    private ExerciseAdapter exerciseAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int workoutIdx;
    private List<ExerciseData> exerciseDatas;
    private WorkoutData workoutData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentEditWorkoutBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_workout, container, false);
        workoutData = getWorkoutData();
        binding.setData(workoutData);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        setDate();

        exerciseDatas = workoutData.getExercises();
        exerciseAdapter = new ExerciseAdapter(exerciseDatas);
        BiPositionListener adapterListener = this::setDoubleClickListener;
        exerciseAdapter.setDoubleListenerMap(exercisePos -> ListenerCreator.openPageMap(adapterListener, exercisePos)); //TODO: wtf

        exerciseAdapter.setDoubleClickListener(adapterListener); //TODO: rename to not be double click
        exerciseAdapter.setLongClickListener(this::setLongClickListener);
        setExerciseButtonListener();
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.table);
        recyclerView.setAdapter(exerciseAdapter);

        linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        scrollScreen();
    }

    private WorkoutData getWorkoutData() {
        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        return Data.getWorkoutDatas().get(workoutIdx);
    }

    private void setDate() {
        DatePickTextView date = view.findViewById(R.id.date);
        date.setTextClickListener();
    }

    private void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Data.EXERCISE_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exerciseDatas.size() - 1 : scrollPosition);
    }

    private void createUndoSnackbar(int position, ExerciseData removedExercise) {
        Snackbar snackbar = Snackbar
                .make(((AppCompatActivity)context).findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.exercise)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    exerciseDatas.add(position, removedExercise);
                    linearLayoutManager.scrollToPosition(position);
                    exerciseAdapter.notifyItemInserted(position);
                    exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
                });
        snackbar.show();
    }

    private void setDoubleClickListener(int exercisePos, int setPos) {
        Bundle bundle = new Bundle();
        bundle.putInt(Data.WORKOUT_IDX, workoutIdx);
        bundle.putInt(Data.EXERCISE_IDX, exercisePos);
        bundle.putInt(Data.SET_IDX, setPos);

        navController.navigate(R.id.action_editWorkoutFragment_to_editExerciseFragment, bundle);
    }

    private void setLongClickListener(int position) {
        ExerciseData removedExercise = exerciseDatas.get(position);
        exerciseDatas.remove(position);
        exerciseAdapter.notifyItemRemoved(position);
        exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
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
        exerciseDatas.add(Data.createEmptyExercise());
        exerciseAdapter.notifyItemInserted(exerciseDatas.size() - 1);
        linearLayoutManager.scrollToPosition(exerciseDatas.size() - 1);
    }

    @Override
    public void onCreatePrevious(DialogFragment dialogFragment) {
        List<WorkoutData> workoutDataList = Data.getWorkoutDatas();
        if (areExercises(workoutDataList)) {
            dialogFragment.dismiss();
            Bundle bundle = new Bundle();
            bundle.putInt(Data.WORKOUT_IDX, workoutIdx);
            navController.navigate(R.id.action_chooseTypeFragment_to_copyExerciseFragment, bundle);
        } else {
            Toast.makeText(requireActivity(), getString(R.string.no_available, getString(R.string.exercise)), Toast.LENGTH_SHORT).show();
        }
    }
}