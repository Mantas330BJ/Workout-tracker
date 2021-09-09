package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public abstract class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.ViewHolder> {
    private Context context;
    private WorkoutListenerClickListener workoutListenerClickListener;
    private WorkoutClickListener clickListener;
    private WorkoutLongClickListener longClickListener;
    private WorkoutListenerLongClickListener workoutListenerLongClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.list_item);
        }

        public ConstraintLayout getConstraintLayout() {
            return constraintLayout;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getConstraintLayout().setOnClickListener(v -> {
            workoutListenerClickListener.onClick(-1).onClick(position);
        });

        holder.getConstraintLayout().setOnLongClickListener(v -> {
            workoutListenerLongClickListener.onClick(0).onLongClick(position); //TODO: do void or something.
            return true;
        });
    }

    public Context getContext() {
        return context;
    }

    public void setWorkoutListenerClickListener(WorkoutListenerClickListener workoutListenerClickListener) {
        this.workoutListenerClickListener = workoutListenerClickListener;
    }

    public void setClickListener(WorkoutClickListener clickListener){
        this.clickListener = clickListener;
    }

    public WorkoutClickListener getClickListener() {
        return clickListener;
    }

    public WorkoutListenerClickListener getWorkoutListenerClickListener() {
        return workoutListenerClickListener;
    }

    public WorkoutListenerLongClickListener getWorkoutListenerLongClickListener() {
        return workoutListenerLongClickListener;
    }

    public void setWorkoutListenerLongClickListener(WorkoutListenerLongClickListener workoutListenerLongClickListener) {
        this.workoutListenerLongClickListener = workoutListenerLongClickListener;
    }

    public void setLongClickListener(WorkoutLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public WorkoutLongClickListener getLongClickListener() {
        return longClickListener;
    }
}

