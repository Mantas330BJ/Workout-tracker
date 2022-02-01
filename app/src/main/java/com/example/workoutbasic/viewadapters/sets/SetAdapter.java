package com.example.workoutbasic.viewadapters.sets;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;
import com.example.workoutbasic.interfaces.listeners.PositionListener;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.SetData;
import com.example.workoutbasic.viewadapters.BindingViewHolder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
    private final List<SetData> setDatas;
    private Map<Integer, PositionListener> listenerMap = new HashMap<>();
    private PositionLongClickListener longClickListener;

    public static class ViewHolder extends BindingViewHolder<ExerciseInfoBinding> {

        public ViewHolder(ExerciseInfoBinding binding) {
            super(binding);
        }

        public View[] getViews() {
            return new View[] {getBinding().set, getBinding().weight, getBinding().reps, getBinding().rir,
                    getBinding().rest, getBinding().comment, getBinding().file};
        }

        public static List<Integer> getIds() {
            return Arrays.asList(R.id.set, R.id.weight, R.id.reps, R.id.rir, R.id.rest, R.id.comment,
                    R.id.file); //TODO: rethink to pass pair or map instead
        }

        public void bind(SetData setData) {
            getBinding().setData(setData);
            getBinding().executePendingBindings();
        }
    }

    public SetAdapter(List<SetData> setDatas) {
        this.setDatas = setDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseInfoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise_info, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        createListeners(holder, position);
        SetData setData = setDatas.get(position);
        holder.bind(setData); //TODO: add real bindings
    }

    private void createListeners(ViewHolder holder, int position) {
        for (View view : holder.getViews()) {
            Optional.ofNullable(listenerMap.get(view.getId())).ifPresent(listener ->
                    view.setOnClickListener(v -> listener.onClick(position)));

            Optional.ofNullable(longClickListener).ifPresent(listener ->
                    view.setOnLongClickListener(v -> {
                        listener.onLongClick(position);
                        return true;
                    })
            );
        }
    }

    public void setListenerMap(Map<Integer, PositionListener> listenerMap) {
        this.listenerMap = listenerMap;
    }

    public void setLongClickListener(PositionLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return setDatas.size();
    }
}
