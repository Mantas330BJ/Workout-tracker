package com.example.workoutbasic.viewadapters.sets;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.viewadapters.BindingViewHolder;

import java.util.List;

public abstract class BaseSetAdapter extends RecyclerView.Adapter<BaseSetAdapter.ViewHolder> {
    protected final List<Set> sets;
    protected PositionLongClickListener longClickListener;

    public static class ViewHolder extends BindingViewHolder<ExerciseInfoBinding> {

        public ViewHolder(ExerciseInfoBinding binding) {
            super(binding);
        }

        public void bind(Set set) {
            binding.setSet(set);
            binding.executePendingBindings();
        }
    }

    protected BaseSetAdapter(List<Set> sets) {
        this.sets = sets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseInfoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise_info, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        createListeners(holder, position);
        Set set = sets.get(position);
        holder.bind(set);
        createListeners(holder, position);
    }

    protected abstract void createListeners(ViewHolder holder, int position);

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }
}
