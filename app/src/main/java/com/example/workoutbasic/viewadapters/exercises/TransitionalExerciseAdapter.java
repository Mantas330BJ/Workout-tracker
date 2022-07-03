package com.example.workoutbasic.viewadapters.exercises;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.viewadapters.sets.TransitionalSetAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class TransitionalExerciseAdapter extends BaseExerciseAdapter {
    private View.OnClickListener clickListener;
    private PositionLongClickListener longClickListener;
    private Bundle adapterParams;

    public TransitionalExerciseAdapter(List<Exercise> listData, Bundle adapterParams) {
        super(listData);
        this.adapterParams = adapterParams;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = listData.get(position);
        holder.bind(exercise);
        RecyclerView recyclerView = holder.getBinding().recyclerView;

        TransitionalSetAdapter setAdapter = new TransitionalSetAdapter(exercise.getSets(), adapterParams);
        recyclerView.setAdapter(setAdapter);
        setListeners(holder, position, setAdapter);
    }

    private void setListeners(ViewHolder holder, int position, TransitionalSetAdapter transitionalSetAdapter) {
        if (clickListener != null) {
            adapterParams.putInt(Constants.EXERCISE_IDX, position);
            holder.itemView.setOnClickListener(clickListener);
            transitionalSetAdapter.setClickListener(clickListener);
        }

        if (longClickListener != null) {
            transitionalSetAdapter.setLongClickListener(childPos -> longClickListener.onLongClick(position));
            holder.itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(position);
                return true;
            });
        }
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
