package com.example.workoutbasic.viewadapters.sets;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.pages.sets.EditExerciseFragment;
import com.example.workoutbasic.viewmodels.SetViewModel;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditableSetAdapter extends BaseSetAdapter {
    private final EditExerciseFragment parent;

    public EditableSetAdapter(List<Set> sets, EditExerciseFragment parent) {
        super(sets);
        this.parent = parent;
    }

    protected void createListeners(ViewHolder holder, int position) {
        ExerciseInfoBinding binding = holder.getBinding();
        Set set = sets.get(position);
        setClickListeners(binding, set);

        if (longClickListener != null) {
            holder.itemView.setOnLongClickListener(parent.getOnLongClickListener());
        }
    }

    private void setClickListeners(ExerciseInfoBinding binding, Set set) {
        SetViewModel viewModel = new ViewModelProvider(parent.requireActivity()).get(SetViewModel.class);
        NavController navController = parent.getNavController();

        binding.weight.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_floatFragment);
            viewModel.selectDouble(new GetterSetter<>(set::getWeight, set::setWeight));
        });
        binding.reps.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_floatFragment);
            viewModel.selectDouble(new GetterSetter<>(set::getReps, set::setReps));
        });
        binding.rir.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_floatFragment);
            viewModel.selectDouble(new GetterSetter<>(set::getReps, set::setReps));
        });
        binding.restSeconds.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_chooseRestFragment);
            viewModel.selectInteger(new GetterSetter<>(set::getRestSeconds, set::setRestSeconds));
        });
        binding.comment.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment);
            viewModel.selectString(new GetterSetter<>(set::getComment, set::setComment));
        });
        binding.file.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_integerFragment);
            viewModel.selectString(new GetterSetter<>(set::getFilePath, set::setFilePath));
        });
    }
}
