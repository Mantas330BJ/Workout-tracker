package com.example.workoutbasic.viewadapters.sets;

import com.example.workoutbasic.interfaces.listeners.IntConsumer;
import com.example.workoutbasic.models.Set;

import java.util.List;

public class TransitionalSetAdapter extends BaseSetAdapter {
    private IntConsumer clickListener;

    public TransitionalSetAdapter(List<Set> sets) {
        super(sets);
    }

    @Override
    protected void createListeners(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> clickListener.consume(position, v));
    }

    public void setClickListener(IntConsumer clickListener) {
        this.clickListener = clickListener;
    }
}
