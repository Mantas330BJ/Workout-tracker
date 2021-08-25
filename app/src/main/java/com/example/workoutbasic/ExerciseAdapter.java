package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends LinearLayoutAdapter {
    private ArrayList<ExerciseData> listData;

    public ExerciseAdapter(ArrayList<ExerciseData> listData) {
        this.listData = listData;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getLinearLayout().removeAllViews();
        final ExerciseData myListData = listData.get(position);

        Exercise exercise = new Exercise(myListData, getContext());
        exercise.initializeExerciseScreen();
        holder.getLinearLayout().addView(exercise.getLayout());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
