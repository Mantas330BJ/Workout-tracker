package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.ViewHolder> {
    private ArrayList<? extends Datas> listdata;
    private Context context;
    private WorkoutClickListener workoutClickListener;
    private WorkoutLongClickListener workoutLongClickListener;
    private boolean addExercise;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list_item);
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }
    }

    public LinearLayoutAdapter(ArrayList<? extends Datas> listdata) {
        this.listdata = listdata;
    }

    public LinearLayoutAdapter(ArrayList<? extends Datas> listdata, boolean addExercise) {
        this.listdata = listdata;
        this.addExercise = addExercise;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(listItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Datas myListData = listdata.get(position);
        holder.getLinearLayout().removeAllViews();
        if (!myListData.emptyChildren()) {
            holder.getLinearLayout().addView(myListData.getLayout(context, addExercise));
        } else {
            listdata.remove(position); //TODO: fix O(n) to O(1)
        }

        holder.getLinearLayout().setOnClickListener(v -> {
            if (workoutClickListener != null) {
                workoutClickListener.onClick(position);
            }
        });

        holder.getLinearLayout().setOnLongClickListener(v -> {
            System.out.println(position);
            if (workoutLongClickListener != null) {
                workoutLongClickListener.onLongClick(position);
            }
            return true;
        });
    }

    public void setClickListener(WorkoutClickListener workoutClickListener){
        this.workoutClickListener = workoutClickListener;
    }

    public void setLongClickListener(WorkoutLongClickListener workoutClickListener) {
        this.workoutLongClickListener = workoutClickListener;
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


}

