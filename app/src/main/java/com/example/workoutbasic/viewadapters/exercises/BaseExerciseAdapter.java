package com.example.workoutbasic.viewadapters.exercises;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.viewadapters.BindingViewHolder;

import java.util.List;

public abstract class BaseExerciseAdapter extends RecyclerView.Adapter<BaseExerciseAdapter.ViewHolder> {
    protected final List<Exercise> listData;
    protected PositionLongClickListener longClickListener;

    protected BaseExerciseAdapter(List<Exercise> listData) {
        this.listData = listData;
    }

    public static class ViewHolder extends BindingViewHolder<ExerciseBinding> {
        public ViewHolder(ExerciseBinding binding) {
            super(binding);
        }

        public void bind(Exercise exercise) {
            binding.setExercise(exercise);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise, parent, false);
        return new ViewHolder(binding);
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}