package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends LinearLayoutAdapter {
    private ArrayList<ExerciseData> listData;
    private View listItem;
    private Context context;

    public ExerciseAdapter(ArrayList<ExerciseData> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //TODO: maybe pass layout id
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        listItem = layoutInflater.inflate(R.layout.exercise, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ExerciseData myListData = listData.get(position);
        WorkoutTextView exerciseName = listItem.findViewById(R.id.exercise);
        exerciseName.setText(myListData.getExercise().toString());

        SetAdapter setAdapter = new SetAdapter(myListData.getSets());
        setAdapter.setClickListener(pos -> getClickListener().onClick(position)); //TODO: add scroll based on pos
        setAdapter.setLongClickListener(getLongClickListener());
        //setAdapter.setParentInfo(position);

        RecyclerView recyclerView = listItem.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(setAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        super.onBindViewHolder(holder, position);
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
