package com.example.workoutbasic.adapters.Exercises;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;

import com.example.workoutbasic.adapters.BindingViewHolder;
import com.example.workoutbasic.adapters.Sets.SetAdapter;
import com.example.workoutbasic.models.ExerciseData;
import com.example.workoutbasic.interfaces.Listeners.DoubleClickListener;
import com.example.workoutbasic.interfaces.Listeners.OnLongClickListener;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>  {
    private DoubleClickListener doubleClickListener;
    private OnLongClickListener onLongClickListener;

    private Supplier<SetAdapter> setAdapterSupplier;
    protected final List<ExerciseData> listData;

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


        Optional.ofNullable(doubleClickListener)
                .ifPresent(getDoubleClickListenerConsumer(holder, position, setAdapter));
        Optional.ofNullable(onLongClickListener)
                .ifPresent(getOnLongClickListenerConsumer(holder, position, setAdapter));
    }

    private Consumer<OnLongClickListener> getOnLongClickListenerConsumer(ViewHolder holder, int position,
                                                                         SetAdapter setAdapter) {
        return listener -> {
            setAdapter.setLongClickListener(childPos -> listener.onLongClick(position));
            holder.itemView.setOnLongClickListener(v -> {
                listener.onLongClick(position);
                return true;
            });
        };
    }

    private Consumer<DoubleClickListener> getDoubleClickListenerConsumer(ViewHolder holder, int position,
                                                                         SetAdapter setAdapter) {
        return listener -> {
            setAdapter.setClickListener(listener.onClick(position));
            holder.itemView.setOnClickListener(v ->
                    listener.onClick(position).onClick(-1) //Click headers.
            );
        };
    }

    public void setSetAdapterSupplier(Supplier<SetAdapter> setAdapterSupplier) {
        this.setAdapterSupplier = setAdapterSupplier;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.onLongClickListener = longClickListener;
    }

    public void setDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }
}
