package com.example.workoutbasic.viewadapters.exercises;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.interfaces.listeners.BiIntConsumer;
import com.example.workoutbasic.interfaces.listeners.IntConsumer;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.viewadapters.sets.TransitionalSetAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class TransitionalExerciseAdapter extends BaseExerciseAdapter {
    private BiIntConsumer setsAdapterBiConsumer;
    private PositionLongClickListener longClickListener;

    public TransitionalExerciseAdapter(List<Exercise> listData) {
        super(listData);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = listData.get(position);
        holder.bind(exercise);
        RecyclerView recyclerView = holder.getBinding().recyclerView;

        TransitionalSetAdapter setAdapter = new TransitionalSetAdapter(exercise.getSets());
        recyclerView.setAdapter(setAdapter);
        setListeners(holder, position, setAdapter);
    }

    private void setListeners(ViewHolder holder, int position, TransitionalSetAdapter transitionalSetAdapter) {
        if (setsAdapterBiConsumer != null) {
            holder.itemView.setOnClickListener(v -> setsAdapterBiConsumer.consume(position, -1, v)); //Click headers
            IntConsumer recyclerListener = (setPos, v) -> setsAdapterBiConsumer.consume(position, setPos, v);
            transitionalSetAdapter.setClickListener(recyclerListener);
        }

        if (longClickListener != null) {
            transitionalSetAdapter.setLongClickListener(childPos -> longClickListener.onLongClick(position));
            holder.itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(position);
                return true;
            });
        }
    }

    public void setSetsAdapterBiConsumer(BiIntConsumer setsAdapterBiConsumer) {
        this.setsAdapterBiConsumer = setsAdapterBiConsumer;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
