package com.example.workoutbasic;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutInfoAdapter extends RecyclerView.Adapter<WorkoutInfoAdapter.ViewHolder> {
    private final ArrayList<ArrayList<String>> listData;
    private Context context;
    private WorkoutClickListener workoutClickListener;

    private int sourceWorkoutIdx = -1;
    private int parentPosition;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView exerciseTextView;
        private final TextView setsTextView;
        private final TextView topWeightTextView;
        private final LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            exerciseTextView = view.findViewById(R.id.exercise);
            setsTextView = view.findViewById(R.id.sets);
            topWeightTextView = view.findViewById(R.id.top_weight);
            layout = view.findViewById(R.id.layout);
        }

        public LinearLayout getLinearLayout() {
            return layout;
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

    public WorkoutInfoAdapter(ArrayList<ArrayList<String>> listData)  {
        this.listData = listData;
    }

    public void setParentInfo(int sourceWorkoutIdx, int parentPosition) {
        this.sourceWorkoutIdx = sourceWorkoutIdx;
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

        holder.getLinearLayout().setOnClickListener(v -> {
            if (sourceWorkoutIdx != -1) {
                ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(sourceWorkoutIdx).getExercises();
                destinationDatas.add(Data.getWorkoutDatas().get(parentPosition).getExercises().get(position));
                Intent intent = new Intent(context, EditWorkoutActivity.class);
                intent.putExtra(Data.WORKOUT_IDX, sourceWorkoutIdx);
                context.startActivity(intent);
            }
        });
    }


    public void setClickListener(WorkoutClickListener workoutClickListener){
        this.workoutClickListener = workoutClickListener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public Context getContext() {
        return context;
    }

}