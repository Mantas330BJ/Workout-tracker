package com.example.workoutbasic.viewadapters.exercises;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;
import com.example.workoutbasic.interfaces.listeners.BiIntConsumer;
import com.example.workoutbasic.interfaces.listeners.IntConsumer;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.viewadapters.BindingViewHolder;
import com.example.workoutbasic.viewadapters.sets.SetAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private final List<Exercise> listData;
    private IntConsumer exerciseNameIntConsumer;
    private IntConsumer headersIntConsumer;
    private BiIntConsumer setsAdapterBiConsumer;
    private PositionLongClickListener longClickListener;

    public ExerciseAdapter(List<Exercise> listData) {
        this.listData = listData;
    }

    public static class ViewHolder extends BindingViewHolder<ExerciseBinding> {
        public ViewHolder(ExerciseBinding binding) {
            super(binding);
        }

        public void bind(Exercise exercise) {
            binding.setExercise(exercise);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = listData.get(position);
        holder.bind(exercise);

        RecyclerView recyclerView = holder.getBinding().recyclerView;
        SetAdapter setAdapter = new SetAdapter(exercise.getSets());
        recyclerView.setAdapter(setAdapter);

        setListeners(holder, position, setAdapter);
    }

    private void setListeners(ViewHolder holder, int position, SetAdapter setAdapter) {
        View view = holder.itemView;

        if (exerciseNameIntConsumer != null) {
            View exerciseName = view.findViewById(R.id.exerciseName);
            exerciseName.setOnClickListener(v -> exerciseNameIntConsumer.consume(position, v));
        }
        if (headersIntConsumer != null) {
            View headers = view.findViewById(R.id.texts_layout);
            headers.setOnClickListener(v -> headersIntConsumer.consume(position, v));
        }
        if (setsAdapterBiConsumer != null) {
            IntConsumer recyclerListener = (setPos, v) -> setsAdapterBiConsumer.consume(position, setPos, v);
            setAdapter.setConsumers(recyclerListener);
        }

        if (longClickListener != null) {
            setAdapter.setLongClickListener(childPos -> longClickListener.onLongClick(position));
            holder.itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(position);
                return true;
            });
        }
    }

    public void setExerciseNameIntConsumer(IntConsumer exerciseNameIntConsumer) {
        this.exerciseNameIntConsumer = exerciseNameIntConsumer;
    }

    public void setHeadersIntConsumer(IntConsumer headersIntConsumer) {
        this.headersIntConsumer = headersIntConsumer;
    }

    public void setSetsAdapterBiConsumer(BiIntConsumer setsAdapterBiConsumer) {
        this.setsAdapterBiConsumer = setsAdapterBiConsumer;
    }

    public void setAllBiConsumers(BiIntConsumer biConsumer) {
        this.exerciseNameIntConsumer = (exercisePos, v) -> biConsumer.consume(exercisePos, -1, v);
        this.headersIntConsumer = (exercisePos, v) -> biConsumer.consume(exercisePos, -1, v);
        this.setsAdapterBiConsumer = biConsumer;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
