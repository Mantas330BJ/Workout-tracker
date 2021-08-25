package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public abstract class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.ViewHolder> {
    private Context context;
    private WorkoutClickListener clickListener;
    private WorkoutLongClickListener longClickListener;
    private boolean addExercise;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list_item);
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(listItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getLinearLayout().setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(position);
            }
        });

        holder.getLinearLayout().setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onLongClick(position);
            }
            return true;
        });
    }

    public Context getContext() {
        return context;
    }

    public void setClickListener(WorkoutClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setLongClickListener(WorkoutLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

}

