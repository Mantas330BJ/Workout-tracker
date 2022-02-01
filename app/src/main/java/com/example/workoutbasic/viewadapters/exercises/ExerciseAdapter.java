package com.example.workoutbasic.viewadapters.exercises;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;
import com.example.workoutbasic.interfaces.listeners.BiPositionListener;
import com.example.workoutbasic.interfaces.listeners.DoubleListenerMap;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.ExerciseData;
import com.example.workoutbasic.viewadapters.BindingViewHolder;
import com.example.workoutbasic.viewadapters.sets.SetAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>  {
    private final List<ExerciseData> listData;

    private BiPositionListener biPositionListener;
    private PositionLongClickListener positionLongClickListener;
    private DoubleListenerMap doubleListenerMap;

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
        //check holder listener or something to prevent spaghetti
        if (biPositionListener != null) {
            setAdapter.setListenerMap(doubleListenerMap.apply(position));
            holder.itemView.setOnClickListener(v ->
                    biPositionListener.onClick(position,-1) //Click headers.
            );
        }

        if (positionLongClickListener != null) {
            setAdapter.setLongClickListener(childPos -> positionLongClickListener.onLongClick(position));
            holder.itemView.setOnLongClickListener(v -> {
                positionLongClickListener.onLongClick(position);
                return true;
            });
        }
    }

    public void setDoubleListenerMap(DoubleListenerMap doubleListenerMap) {
        this.doubleListenerMap = doubleListenerMap;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.positionLongClickListener = longClickListener;
    }

    public void setDoubleClickListener(BiPositionListener biPositionListener) {
        this.biPositionListener = biPositionListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
