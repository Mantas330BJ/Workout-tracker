package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class SetAdapter extends LinearLayoutAdapter {
    private ArrayList<SetData> listData;

    public SetAdapter(ArrayList<SetData> listData) {
        this.listData = listData;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getLinearLayout().removeAllViews();
        final SetData myListData = listData.get(position);

        Set set = new Set(myListData, getContext(), true);
        holder.getLinearLayout().addView(set.getLayout());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
