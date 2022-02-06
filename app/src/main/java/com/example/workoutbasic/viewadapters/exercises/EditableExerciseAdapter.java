package com.example.workoutbasic.viewadapters.exercises;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.viewadapters.sets.EditableSetAdapter;

import java.util.List;

public class EditableExerciseAdapter extends BaseExerciseAdapter {
    private final FragmentActivity activity;
    private final NavController navController;

    public EditableExerciseAdapter(List<Exercise> listData, FragmentActivity activity, NavController navController) {
        super(listData);
        this.activity = activity;
        this.navController = navController;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = listData.get(position);
        holder.bind(exercise);
        RecyclerView recyclerView = holder.getBinding().recyclerView;

        EditableSetAdapter setAdapter = new EditableSetAdapter(exercise.getSets(), activity, navController);
        recyclerView.setAdapter(setAdapter);
        setListeners(holder, setAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setListeners(ViewHolder holder, EditableSetAdapter setAdapter) {
        View view = holder.itemView;
        View exerciseName = view.findViewById(R.id.exerciseName);
        exerciseName.setOnClickListener(v -> {}); //TODO: add real listener

        setAdapter.setLongClickListener(longClickListener);
    }
}
