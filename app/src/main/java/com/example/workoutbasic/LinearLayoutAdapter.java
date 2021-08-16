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


public class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.ViewHolder>{
    private ArrayList<WorkoutData> listdata;
    private Context context;
    private WorkoutListener listener;

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

    public LinearLayoutAdapter(ArrayList<WorkoutData> listdata) {
        this.listdata = listdata;
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
        final WorkoutData myListData = listdata.get(position);
        holder.getLinearLayout().removeAllViews();
        holder.getLinearLayout().addView(new Workout(myListData, context, Data.WORKOUT).getLayout());
        holder.getLinearLayout().setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(position);
            }
        });
    }

    public void setListener(WorkoutListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


}

