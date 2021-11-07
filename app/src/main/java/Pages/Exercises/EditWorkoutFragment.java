package Pages.Exercises;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Pages.Dialogs.ChooseTypeFragment;
import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.FragmentEditExerciseBinding;
import com.example.workoutbasic.databinding.FragmentEditWorkoutBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.Exercises.ExerciseAdapter;
import Interfaces.ButtonOptions;
import DataEdit.TextViews.DatePickTextView;
import Datas.ExerciseData;
import Datas.WorkoutData;
import Pages.NavigationFragment;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutFragment extends NavigationFragment implements ButtonOptions {
    private ExerciseAdapter exerciseAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int workoutIdx;
    private ArrayList<ExerciseData> exerciseDatas;

    private WorkoutData workoutData;

    @Nullable
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
        setRecyclerView();

        setExerciseButtonListener();
        setDoubleClickListener();
        setLongClickListener();
    }

    public void setRecyclerView() {
        exerciseDatas = workoutData.getExercises();
        exerciseAdapter = new ExerciseAdapter(exerciseDatas);

        RecyclerView recyclerView = view.findViewById(R.id.table);
        recyclerView.setAdapter(exerciseAdapter);

        linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        scrollScreen();
    }

    public WorkoutData getWorkoutData() {
        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        return Data.getWorkoutDatas().get(workoutIdx);
    }

    public void setDate() {
        DatePickTextView date = view.findViewById(R.id.date);
        date.setTextClickListener();
    }

    public void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Data.EXERCISE_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exerciseDatas.size() - 1 : scrollPosition);
    }

    public boolean areExercises() {
        for (WorkoutData workoutData : Data.getWorkoutDatas()) {
            if (!workoutData.getExercises().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void createUndoSnackbar(int position, ExerciseData removedExercise) {
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

    public void setDoubleClickListener() {
        exerciseAdapter.setDoubleClickListener(exerciseIdx -> setIdx -> {
            Bundle bundle = new Bundle();
            bundle.putInt(Data.WORKOUT_IDX, workoutIdx);
            bundle.putInt(Data.EXERCISE_IDX, exerciseIdx);
            bundle.putInt(Data.SET_IDX, setIdx);

            navController.navigate(R.id.action_editWorkoutFragment_to_editExerciseFragment, bundle);
        });
    }

    public void setLongClickListener() {
        exerciseAdapter.setLongClickListener(position -> {
            ExerciseData removedExercise = exerciseDatas.get(position);
            exerciseDatas.remove(position);
            exerciseAdapter.notifyItemRemoved(position);
            exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
            createUndoSnackbar(position, removedExercise);
        });
    }

    public void setExerciseButtonListener() {
        Button exerciseButton = view.findViewById(R.id.exercise_button);
        exerciseButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(ChooseTypeFragment.NAME_KEY, context.getString(R.string.exercise));
            navController.navigate(R.id.action_editWorkoutFragment_to_chooseTypeFragment, bundle);
        });
    }

    @Override
    public View.OnClickListener onCreateEmpty(DialogFragment dialogFragment) {
        return v -> {
            dialogFragment.dismiss();
            exerciseDatas.add(Data.createEmptyExercise());
            exerciseAdapter.notifyItemInserted(exerciseDatas.size() - 1);
            linearLayoutManager.scrollToPosition(exerciseDatas.size() - 1);
        };
    }

    @Override
    public View.OnClickListener onCreatePrevious(DialogFragment dialogFragment) {
        return v -> {
            if (!areExercises()) {
                dialogFragment.dismiss();
                Bundle bundle = new Bundle();
                bundle.putInt(Data.WORKOUT_IDX, workoutIdx);
                navController.navigate(R.id.action_chooseTypeFragment_to_copyExerciseFragment, bundle);
            } else {
                Toast.makeText(requireActivity(), getString(R.string.no_available, getString(R.string.exercise)), Toast.LENGTH_SHORT).show();
            }
        };
    }
}