package com.example.workoutbasic.viewadapters.workouts;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.models.Workout;
import com.example.workoutbasic.pages.workouts.HistoryFragment;
import com.example.workoutbasic.utils.StringConverter;
import com.example.workoutbasic.utils.WorkoutDisplayer;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private final List<Workout> listData;
    private final HistoryFragment parent;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }
    }

    public WorkoutAdapter(List<Workout> listData, HistoryFragment parent) {
        this.listData = listData;
        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(layoutInflater.inflate(R.layout.workout, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Workout workout = listData.get(position);
        Bundle adapterParams = parent.getAdapterParams();
        adapterParams.putInt(Constants.WORKOUT_IDX, position);

        TextView date = holder.date; //TODO: look for auto binding
        date.setText(StringConverter.convertDate(workout.getWorkoutDate()));

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(
                WorkoutDisplayer.extractMainWorkoutInfo(workout),
                parent
        );

        RecyclerView recyclerView = holder.recyclerView;
        recyclerView.setAdapter(workoutInfoAdapter);

        holder.itemView.setOnClickListener(parent.getOnClickListener());
        holder.itemView.setOnLongClickListener(parent.getOnLongClickListener());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}


