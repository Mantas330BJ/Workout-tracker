package com.example.workoutbasic.adapters.Sets;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;

import java.util.List;
import java.util.Optional;

import com.example.workoutbasic.adapters.BindingViewHolder;
import com.example.workoutbasic.models.SetData;
import com.example.workoutbasic.interfaces.Input.WorkoutInput;
import com.example.workoutbasic.interfaces.Listeners.OnClickListener;
import com.example.workoutbasic.interfaces.Listeners.OnLongClickListener;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
    private final List<SetData> setDatas;
    private OnLongClickListener longClickListener;
    private OnClickListener clickListener;
    private Runnable listenerApplier;

    public static class ViewHolder extends BindingViewHolder<ExerciseInfoBinding> {

        public ViewHolder(ExerciseInfoBinding binding) {
            super(binding);
        }

        public WorkoutInput[] getViews() {
            return new WorkoutInput[] {getBinding().set, getBinding().weight, getBinding().reps, getBinding().rir,
                    getBinding().rest, getBinding().comment, getBinding().file};
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

    public void createListeners(ViewHolder holder, int position) {
        int pos = 0;
        for (WorkoutInput workoutInput : holder.getViews()) {

            e1(pos, workoutInput).run();

            Optional.ofNullable(longClickListener).ifPresent(listener ->
                    workoutInput.setOnLongClickListener(v -> {
                        longClickListener.onLongClick(position);
                        return true;
                    })
            );
            ++pos;
        }
    }

    private Runnable e2(int position, WorkoutInput workoutInput) {
        int setPosition = 0;
        return () -> {
            if (position != setPosition) {
                workoutInput.setTextClickListener();
            }
        };
    }

    private Runnable e1(int pos, WorkoutInput workoutInput) {
        return () ->
        Optional.ofNullable(clickListener).ifPresent(listener ->
                workoutInput.setOnClickListener(v -> listener.onClick(pos))
        );
    }


    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return setDatas.size();
    }
}
