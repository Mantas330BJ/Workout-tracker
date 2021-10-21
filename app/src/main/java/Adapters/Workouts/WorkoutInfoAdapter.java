package Adapters.Workouts;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;
import com.example.workoutbasic.databinding.WorkoutInfoBinding;

import java.util.ArrayList;

import Adapters.BindingViewHolder;
import Interfaces.Listeners.NestedListenerPasser;

public class WorkoutInfoAdapter extends RecyclerView.Adapter<BindingViewHolder<WorkoutInfoBinding>> {
    private final ArrayList<ArrayList<String>> listData;
    private int parentPosition;
    private final NestedListenerPasser parent;

    public WorkoutInfoAdapter(ArrayList<ArrayList<String>> listData, NestedListenerPasser parent)  {
        this.listData = listData;
        this.parent = parent;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    @NonNull
    @Override
    public BindingViewHolder<WorkoutInfoBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        WorkoutInfoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.workout_info, parent, false);
        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<WorkoutInfoBinding> holder, int position) {
        holder.binding.exercise.setText(listData.get(position).get(0));
        holder.binding.sets.setText(listData.get(position).get(1));
        holder.binding.topWeight.setText(listData.get(position).get(2));

        holder.itemView.setOnClickListener(v -> parent.getDoubleClickListener().onClick(parentPosition).onClick(position));

        holder.itemView.setOnLongClickListener(v -> {
            parent.getOnLongClickListener().onLongClick(parentPosition);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}