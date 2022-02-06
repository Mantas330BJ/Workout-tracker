package com.example.workoutbasic.viewadapters.sets;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.viewmodels.SetViewModel;

import java.util.List;
import java.util.Optional;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditableSetAdapter extends BaseSetAdapter {
    private PositionLongClickListener longClickListener;
    private final FragmentActivity activity;
    private final NavController navController;

    public EditableSetAdapter(List<Set> sets, FragmentActivity activity, NavController navController) {
        super(sets);
        this.activity = activity;
        this.navController = navController;
    }

    protected void createListeners(ViewHolder holder, int position) {
        SetViewModel viewModel = new ViewModelProvider(activity).get(SetViewModel.class);
        ExerciseInfoBinding binding = holder.getBinding();
        Set set = sets.get(position);
        binding.weight.setOnClickListener(v -> {
            navController.navigate(R.id.action_editExerciseFragment_to_floatFragment);
            viewModel.selectDouble(new GetterSetter<>(set::getWeight, set::setWeight));
        });
        binding.reps.setOnClickListener(v -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        binding.rir.setOnClickListener(v -> navController.navigate(R.id.action_editExerciseFragment_to_floatFragment));
        binding.restSeconds.setOnClickListener(v -> navController.navigate(R.id.action_editExerciseFragment_to_chooseRestFragment));
        binding.comment.setOnClickListener(v -> navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment));
        binding.file.setOnClickListener(v -> navController.navigate(R.id.action_editExerciseFragment_to_integerFragment));

        Optional.ofNullable(longClickListener).ifPresent(listener ->
                holder.itemView.setOnLongClickListener(v -> {
                    listener.onLongClick(position);
                    return true;
                })
        );
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
