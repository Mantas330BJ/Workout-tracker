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
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        listItem = layoutInflater.inflate(R.layout.workout, parent, false);
        return new ViewHolder(listItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final WorkoutData myListData = listData.get(position);


        TextView date = listItem.findViewById(R.id.date);
        date.setText(myListData.getDate().toString());

        Workout workout = new Workout(myListData);
        workout.setAddExercise(addExercise);

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(workout.getMainWorkoutInfo());
        workoutInfoAdapter.setWorkoutLongClickListener(getLongClickListener()); //Logic for table items.
        workoutInfoAdapter.setWorkoutClickListener(getWorkoutListenerClickListener().onClick(position));

        RecyclerView recyclerView = listItem.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(workoutInfoAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        holder.getConstraintLayout().setOnClickListener(v -> {
            getWorkoutListenerClickListener().onClick(position).onClick(-1); //Click headers.
        });

        holder.getConstraintLayout().setOnLongClickListener(v -> {
            getLongClickListener().onLongClick(position);
            return true;
        });
    }


    @Override
    public void setWorkoutListenerClickListener(WorkoutListenerClickListener workoutClickListener) {
        super.setWorkoutListenerClickListener(workoutClickListener);
        notifyDataSetChanged(); //Sounds fishy and should not prevent bugs.
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    /*
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
     */
}


