package Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;

import java.util.ArrayList;

import Interfaces.NestedListenerPasser;

public class WorkoutInfoAdapter extends RecyclerView.Adapter<WorkoutInfoAdapter.ViewHolder> {
    private final ArrayList<ArrayList<String>> listData;
    private int parentPosition;
    private NestedListenerPasser parent;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView exerciseTextView;
        private final TextView setsTextView;
        private final TextView topWeightTextView;

        public ViewHolder(View view) {
            super(view);
            exerciseTextView = view.findViewById(R.id.exercise);
            setsTextView = view.findViewById(R.id.sets);
            topWeightTextView = view.findViewById(R.id.top_weight);
        }

        public TextView getExerciseTextView() {
            return exerciseTextView;
        }

        public TextView getSetsTextView() {
            return setsTextView;
        }

        public TextView getTopWeightTextView() {
            return topWeightTextView;
        }
    }

    public WorkoutInfoAdapter(ArrayList<ArrayList<String>> listData, NestedListenerPasser parent)  {
        this.listData = listData;
        this.parent = parent;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.workout_info, parent, false);
        return new ViewHolder(listItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getExerciseTextView().setText(listData.get(position).get(0));
        holder.getSetsTextView().setText(listData.get(position).get(1));
        holder.getTopWeightTextView().setText(listData.get(position).get(2));

        holder.itemView.setOnClickListener(v -> {
            parent.getDoubleClickListener().onClick(parentPosition).onClick(position);
        });

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