package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WorkoutAdapter extends LinearLayoutAdapter {
    private ArrayList<WorkoutData> listData;
    private boolean addExercise;

    public WorkoutAdapter(ArrayList<WorkoutData> listData, boolean addExercise) {
        this.listData = listData;
        this.addExercise = addExercise;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getLinearLayout().removeAllViews();


        final WorkoutData myListData = listData.get(position);
        Workout workout = new Workout(myListData, getContext());
        workout.setAddExercise(addExercise);
        workout.initializeMainScreenWorkout();
        holder.getLinearLayout().addView(workout.getLayout());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
