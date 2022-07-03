package com.example.workoutbasic.viewadapters.exercises;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.pages.sets.EditExerciseFragment;
import com.example.workoutbasic.viewadapters.sets.EditableSetAdapter;

import java.util.List;

public class EditableExerciseAdapter extends BaseExerciseAdapter {
    private final EditExerciseFragment parent;

    public EditableExerciseAdapter(List<Exercise> listData, EditExerciseFragment parent) {
        super(listData);
        this.parent = parent;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = listData.get(position);
        holder.bind(exercise);
        RecyclerView recyclerView = holder.getBinding().recyclerView;

        EditableSetAdapter setAdapter = new EditableSetAdapter(exercise.getSets(), parent);
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
