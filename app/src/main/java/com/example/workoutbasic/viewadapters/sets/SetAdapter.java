package com.example.workoutbasic.viewadapters.sets;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;
import com.example.workoutbasic.interfaces.listeners.IntConsumer;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.viewadapters.BindingViewHolder;

import java.util.List;
import java.util.Optional;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
    private final List<Set> sets;
    private IntConsumer consumers;
    private PositionLongClickListener longClickListener;

    public static class ViewHolder extends BindingViewHolder<ExerciseInfoBinding> {

        public ViewHolder(ExerciseInfoBinding binding) {
            super(binding);
        }

        public View[] getViews() {
            return new View[]{binding.set, binding.weight, binding.reps, binding.rir,
                    binding.rest, binding.comment, binding.file};
        }

        public void bind(Set set) {
            binding.setData(set);
            binding.executePendingBindings();
        }
    }

    public SetAdapter(List<Set> sets) {
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        createListeners(holder, position);
        Set set = sets.get(position);
        holder.bind(set); //TODO: add real bindings
    }

    private void createListeners(ViewHolder holder, int position) {
        for (View view : holder.getViews()) {
            Optional.ofNullable(consumers)
                    .ifPresent(consumer ->
                            view.setOnClickListener(v -> consumer.consume(position, v))
                    );
        }
        Optional.ofNullable(longClickListener).ifPresent(listener ->
                holder.itemView.setOnLongClickListener(v -> {
                    listener.onLongClick(position);
                    return true;
                })
        );
    }

    public void setConsumers(IntConsumer consumers) {
        this.consumers = consumers;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }
}
