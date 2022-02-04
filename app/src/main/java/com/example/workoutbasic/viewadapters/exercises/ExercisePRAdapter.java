package com.example.workoutbasic.viewadapters.exercises;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseprBinding;
import com.example.workoutbasic.interfaces.listeners.IntConsumer;
import com.example.workoutbasic.models.ExercisePRData;
import com.example.workoutbasic.viewadapters.BindingViewHolder;

import java.util.List;

public class ExercisePRAdapter extends RecyclerView.Adapter<ExercisePRAdapter.ViewHolder> {
    private List<ExercisePRData> exercisePRData;
    private IntConsumer intConsumer;

    public static class ViewHolder extends BindingViewHolder<ExerciseprBinding> {

        public ViewHolder(ExerciseprBinding binding) {
            super(binding);
        }

        public void bind(ExercisePRData prData) {
//            getBinding().setData(prData);
            getBinding().executePendingBindings();
        }
    }

    public ExercisePRAdapter(List<ExercisePRData> exercisePRData) {
        this.exercisePRData = exercisePRData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseprBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercisepr, parent, false);
        return new ExercisePRAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ExercisePRData prData = exercisePRData.get(position);
        holder.bind(prData);
        holder.itemView.setOnClickListener(v -> {
            intConsumer.consume(position, v);
        });
    }

    @Override
    public int getItemCount() {
        return exercisePRData.size();
    }

    public void setOnClickListener(IntConsumer intConsumer) {
        this.intConsumer = intConsumer;
    }
}
