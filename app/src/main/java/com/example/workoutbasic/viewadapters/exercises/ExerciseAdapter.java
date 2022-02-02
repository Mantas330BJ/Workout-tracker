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
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.ExerciseData;
import com.example.workoutbasic.viewadapters.BindingViewHolder;
import com.example.workoutbasic.viewadapters.sets.SetAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>  {
    private final List<ExerciseData> listData;
    private BiIntConsumer biIntConsumer;
    private PositionLongClickListener longClickListener;

    public ExerciseAdapter(List<ExerciseData> listData) {
        this.listData = listData;
    }

    public static class ViewHolder extends BindingViewHolder<ExerciseBinding> {
        public ViewHolder(ExerciseBinding binding) {
            super(binding);
        }

        public void bind(ExerciseData exerciseData) {
            getBinding().setData(exerciseData);
            getBinding().executePendingBindings();
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
        ExerciseData exerciseData = listData.get(position);
        holder.bind(exerciseData);

        RecyclerView recyclerView = holder.getBinding().recyclerView;
        SetAdapter setAdapter = new SetAdapter(exerciseData.getSets());
        recyclerView.setAdapter(setAdapter);

        setListeners(holder, position, setAdapter);
    }

    private void setListeners(ViewHolder holder, int position, SetAdapter setAdapter) {
        if (biIntConsumer != null) {
            View headers = holder.itemView.findViewById(R.id.texts_layout); //Three listeners. Exercise, header, recycler
            headers.setOnClickListener(v -> biIntConsumer.consume(position, -1));

            BiIntConsumer consumer = (id, setPos) -> biIntConsumer.consume(position, setPos);

            setAdapter.setConsumers(consumer);
        }

        if (longClickListener != null) {
            setAdapter.setLongClickListener(childPos -> longClickListener.onLongClick(position));
            holder.itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(position);
                return true;
            });
        }
    }

    public void setBiIntConsumer(BiIntConsumer biIntConsumer) {
        this.biIntConsumer = biIntConsumer;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
