package com.example.workoutbasic.viewadapters.workouts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.WorkoutInfoBinding;
import com.example.workoutbasic.models.WorkoutInfo;

import java.util.List;

import com.example.workoutbasic.viewadapters.BindingViewHolder;
import com.example.workoutbasic.interfaces.Listeners.NestedListenerPasser;

public class WorkoutInfoAdapter extends RecyclerView.Adapter<BindingViewHolder<WorkoutInfoBinding>> {
    private final List<WorkoutInfo> listData;
    private final int parentPosition;
    private final NestedListenerPasser parent;

    public WorkoutInfoAdapter(List<WorkoutInfo> listData, NestedListenerPasser parent, int parentPosition)  {
        this.listData = listData;
        this.parentPosition = parentPosition;
        this.parent = parent;
    }

    @NonNull
    @Override
    public BindingViewHolder<WorkoutInfoBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        WorkoutInfoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.workout_info, parent, false);
        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<WorkoutInfoBinding> holder, int position) {
        holder.getBinding().exercise.setText(listData.get(position).getExercise());
        holder.getBinding().sets.setText(listData.get(position).getSets());
        holder.getBinding().topWeight.setText(listData.get(position).getFormattedTopWeight());

        holder.itemView.setOnClickListener(v ->
                parent.getDoubleClickListener().onClick(parentPosition).onClick(position));

        holder.itemView.setOnLongClickListener(v -> {
            parent.getOnLongClickListener().onLongClick(parentPosition);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}