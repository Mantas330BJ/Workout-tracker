package com.example.workoutbasic.viewadapters.sets;

import android.os.Bundle;
import android.view.View;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.models.Set;

import java.util.List;

public class TransitionalSetAdapter extends BaseSetAdapter {
    private View.OnClickListener clickListener;
    private final Bundle adapterParams;

    public TransitionalSetAdapter(List<Set> sets, Bundle adapterParams) {
        super(sets);
        this.adapterParams = adapterParams;
    }

    @Override
    protected void createListeners(ViewHolder holder, int position) {
        adapterParams.putInt(Constants.SET_IDX, position);
        holder.itemView.setOnClickListener(clickListener);
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
