package Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.WorkoutDisplayer;
import Datas.WorkoutData;

import java.util.ArrayList;

import Interfaces.DoubleClickListener;
import Interfaces.OnLongClickListener;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private ArrayList<WorkoutData> listData;
    private Context context;
    private View listItem;

    private DoubleClickListener doubleClickListener;
    private OnLongClickListener longClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout constraintLayout;
        private final TextView date;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.list_item);
            date = itemView.findViewById(R.id.date);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }

        public ConstraintLayout getConstraintLayout() {
            return constraintLayout;
        }

        public TextView getDate() {
            return date;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }

    public WorkoutAdapter(ArrayList<WorkoutData> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //TODO: maybe pass layout id
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        listItem = layoutInflater.inflate(R.layout.workout, parent, false);
        return new ViewHolder(listItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WorkoutData myListData = listData.get(position);

        TextView date = holder.getDate();
        date.setText(myListData.getDate().toString());

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(WorkoutDisplayer.getMainWorkoutInfo(myListData));
        workoutInfoAdapter.setWorkoutLongClickListener(childPos -> longClickListener.onLongClick(position)); //Logic for table items.
        workoutInfoAdapter.setWorkoutClickListener(doubleClickListener.onClick(position));

        RecyclerView recyclerView = holder.getRecyclerView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(workoutInfoAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        holder.getConstraintLayout().setOnClickListener(v -> {
            doubleClickListener.onClick(position).onClick(-1); //Click headers.
        });

        holder.getConstraintLayout().setOnLongClickListener(v -> {
            longClickListener.onLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }
}


