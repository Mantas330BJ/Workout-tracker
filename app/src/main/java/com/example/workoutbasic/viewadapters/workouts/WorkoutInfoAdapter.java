package com.example.workoutbasic.viewadapters.workouts;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.WorkoutInfoBinding;
import com.example.workoutbasic.models.WorkoutInfo;
import com.example.workoutbasic.pages.workouts.HistoryFragment;
import com.example.workoutbasic.viewadapters.BindingViewHolder;

import java.util.List;

public class WorkoutInfoAdapter extends RecyclerView.Adapter<BindingViewHolder<WorkoutInfoBinding>> {
    private final List<WorkoutInfo> listData;
    private final HistoryFragment parent;

    public WorkoutInfoAdapter(List<WorkoutInfo> listData, HistoryFragment parent)  {
        this.listData = listData;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(BindingViewHolder<WorkoutInfoBinding> holder, int position) {
        WorkoutInfo workoutInfo = listData.get(position);
        holder.getBinding().exerciseName.setText(workoutInfo.getExercise()); //TODO: add real bindings
        holder.getBinding().sets.setText(workoutInfo.getSets());
        holder.getBinding().topWeight.setText(workoutInfo.getFormattedTopWeight());

        Bundle adapterParams = parent.getAdapterParams();
        adapterParams.putInt(Constants.EXERCISE_IDX, position);

        holder.itemView.setOnClickListener(parent.getOnClickListener());
        holder.itemView.setOnLongClickListener(parent.getOnLongClickListener());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}