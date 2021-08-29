package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WorkoutAdapter extends LinearLayoutAdapter {
    private ArrayList<WorkoutData> listData;
    private boolean addExercise;
    private Context context;
    private View listItem;

    public WorkoutAdapter(ArrayList<WorkoutData> listData, boolean addExercise) {
        this.listData = listData;
        this.addExercise = addExercise;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //TODO: maybe pass layout id
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        listItem = layoutInflater.inflate(R.layout.workout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.getLinearLayout().removeAllViews();
        final WorkoutData myListData = listData.get(position);
        WorkoutTextView date = listItem.findViewById(R.id.date);
        date.setText(myListData.getDate().toString());

        Workout workout = new Workout(myListData, getContext());
        workout.setAddExercise(addExercise);

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(workout.getMainWorkoutInfo());
        RecyclerView recyclerView = listItem.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(workoutInfoAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

