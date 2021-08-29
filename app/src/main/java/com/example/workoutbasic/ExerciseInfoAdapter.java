package com.example.workoutbasic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseInfoAdapter extends RecyclerView.Adapter<ExerciseInfoAdapter.ViewHolder> {
    private final ArrayList<ArrayList<String>> listData;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView setTextView;
        private final TextView weightTextView;
        private final TextView repsTextView;
        private final TextView rirTextView;
        private final TextView restTextView;

        private final LinearLayout layout;


        public ViewHolder(View view) {
            super(view);
            setTextView = view.findViewById(R.id.set);
            repsTextView = view.findViewById(R.id.reps);
            weightTextView = view.findViewById(R.id.weight);
            rirTextView = view.findViewById(R.id.rir);
            restTextView = view.findViewById(R.id.rest);
            layout = view.findViewById(R.id.layout);
        }

        public LinearLayout getLinearLayout() {
            return layout;
        }

        public TextView getSetTextView() {
            return setTextView;
        }

        public TextView getWeightTextView() {
            return weightTextView;
        }

        public TextView getRepsTextView() {
            return repsTextView;
        }

        public TextView getRirTextView() {
            return rirTextView;
        }

        public TextView getRestTextView() {
            return restTextView;
        }
    }

    public ExerciseInfoAdapter(ArrayList<ArrayList<String>> listData) {
        this.listData = listData;
    }

    @Override
    public ExerciseInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.exercise_info, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseInfoAdapter.ViewHolder holder, int position) {
        holder.getSetTextView().setText(listData.get(position).get(0));
        holder.getWeightTextView().setText(listData.get(position).get(1));
        holder.getRepsTextView().setText(listData.get(position).get(2));
        holder.getRestTextView().setText(listData.get(position).get(3));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
