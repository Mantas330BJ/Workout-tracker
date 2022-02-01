package com.example.workoutbasic.viewadapters.workouts;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.listeners.NestedListenerPasser;
import com.example.workoutbasic.models.WorkoutData;
import com.example.workoutbasic.utils.WorkoutDisplayer;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private final List<WorkoutData> listData;
    private final NestedListenerPasser parent;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }
    }

    public WorkoutAdapter(List<WorkoutData> listData, NestedListenerPasser parent) {
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
        final WorkoutData myListData = listData.get(position);

        TextView date = holder.date;
        date.setText(myListData.getDate().toString());

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(
                WorkoutDisplayer.extractMainWorkoutInfo(myListData),
                parent,
                position
        );

        RecyclerView recyclerView = holder.recyclerView;
        recyclerView.setAdapter(workoutInfoAdapter);

        holder.itemView.setOnClickListener(v ->
            parent.getDoubleClickListener().onClick(position,-1) //Click headers.
        );

        holder.itemView.setOnLongClickListener(v -> {
            parent.getOnLongClickListener().onLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}


